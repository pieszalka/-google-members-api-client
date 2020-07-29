package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import com.fourthwall.googlemembersapi.client.domain.repository.ChannelMembershipsRepository
import com.fourthwall.googlemembersapi.client.domain.repository.CreatorsTokensRepository
import com.fourthwall.googlemembersapi.client.domain.repository.Token
import org.openapitools.client.models.MemberListDto
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class RefreshMembershipsTask(
        val tokensRepository: CreatorsTokensRepository,
        val channelMembershipsRepository: ChannelMembershipsRepository
) {

    val googleYoutubeApiUrl = "https://www.googleapis.com/"

    private val logger = LoggerFactory.getLogger(this::class.java.name.substringBefore("\$Companion"))

    @Scheduled(fixedDelay = 10000)
    fun refresh() {
        logger.info("Start refreshing memberships")
        val tokens = tokensRepository.getAllTokens()
        logger.info("Found " + tokens.size + " creators")
        tokens.forEach { token ->
            val api = GoogleApiDomain.create(googleYoutubeApiUrl, token.accessToken)
            val members = api.listAllMembers("snippet", 100).toModel()
            if (members != null) {
                logger.info("Found " + (members.items?.size ?: 0) + " members")
                saveMembers(members)
            } else {
                logger.info("Refreshing access token")
                tokensRepository.removeToken(token)
                val newToken = api.refreshToken("644395585450-pbmp0pvg13nfl5e9tpk3s454ekt8ldo3.apps.googleusercontent.com",
                        "EGl_R5NoSJ_2Govbem4gIXBO",
                        token.refreshToken
                ).toModel()!!
                val newApi = GoogleApiDomain.create(googleYoutubeApiUrl, newToken.accessToken)
                val newMembers = api.listAllMembers("snippet", 100).toModel()
                if (newMembers != null) {
                    logger.info("Found " + (newMembers.items?.size ?: 0) + " members")
                    saveMembers(newMembers)
                    tokensRepository.saveToken(Token(newToken.accessToken, token.refreshToken ?: ""))
                } else {
                    logger.info("Members not found")
                }
            }
        }
    }

    private fun saveMembers(members: MemberListDto): Unit {
        members.items
                ?.map { Pair(it.snippet.creatorChannelId, it.snippet.memberDetails.channelId) }
                ?.groupBy { it.first }
                ?.forEach { (creatorChannelId, channels) -> channelMembershipsRepository.saveMemberships(creatorChannelId, channels.map { it.second }) }

    }

    fun <A : Throwable, B> Either<A, B>.toModel(): B? =
            when (this) {
                is Either.Left -> {
                    println("Error: " + a.message)
                    null
                }
                is Either.Right -> b
            }
}

