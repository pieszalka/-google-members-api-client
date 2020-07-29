package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import com.fourthwall.googlemembersapi.client.domain.model.ChannelListDto
import com.fourthwall.googlemembersapi.client.domain.model.ProfileDto
import com.fourthwall.googlemembersapi.client.domain.model.RefreshTokenDto
import org.openapitools.client.models.MemberListDto
import org.openapitools.client.models.MembershipLevelListDto
import org.slf4j.LoggerFactory

interface GoogleApi : GoogleYoutubeMembersApi, GoogleYoutubeMembershipsLevelsApi, GoogleYoutubeDataApi, GoogleProfileDataApi

interface GoogleYoutubeMembersApi {
    fun listAllMembers(part: String, maxResults: Int): Either<Throwable, MemberListDto>
    fun checkUsersForTheirMemberships(part: String, filterByMemberChannelId: String): Either<Throwable, MemberListDto>
    fun listMembersUpdates(part: String, mode: String): Either<Throwable, MemberListDto>
    fun listMembers(part: String, maxResults: Int, filterByMemberChannelId: String, mode: String, pageToken: String, hasAccessToLevel: String): Either<Throwable, MemberListDto>
}

interface GoogleYoutubeMembershipsLevelsApi {
    fun listAllPricingLevels(part: String): Either<Throwable, MembershipLevelListDto>
}

interface GoogleYoutubeDataApi {
    fun getChannelInfo(): Either<Throwable, ChannelListDto>
}

interface GoogleProfileDataApi {
    fun getUserInfo(): Either<Throwable, ProfileDto>
    fun refreshToken(clientId: String, clientSecret: String, refreshToken: String): Either<Throwable, RefreshTokenDto>
}

open class GoogleApiDomain(
        private val membersApi: GoogleYoutubeMembersApi,
        private val membershipsLevelsApi: GoogleYoutubeMembershipsLevelsApi,
        private val youtubeApi: GoogleYoutubeDataApi,
        private val profileAPi: GoogleProfileDataApi

) : GoogleApi {

    private val logger = LoggerFactory.getLogger(this::class.java.name.substringBefore("\$Companion"))

    override fun listAllMembers(part: String, maxResults: Int): Either<Throwable, MemberListDto> {
        logger.info("list all members: part=$part, maxResults=$maxResults")
        return membersApi.listAllMembers(part, maxResults)
    }

    override fun checkUsersForTheirMemberships(part: String, filterByMemberChannelId: String): Either<Throwable, MemberListDto> {
        logger.info("check users for their memberships: part=$part, filterByMemberChannelId=$filterByMemberChannelId")
        return membersApi.checkUsersForTheirMemberships(part, filterByMemberChannelId)
    }

    override fun listMembersUpdates(part: String, mode: String): Either<Throwable, MemberListDto> {
        logger.info("list members updates: part=$part, mode=$mode")
        return membersApi.listMembersUpdates(part, mode)
    }

    override fun listMembers(part: String, maxResults: Int, filterByMemberChannelId: String, mode: String, pageToken: String, hasAccessToLevel: String): Either<Throwable, MemberListDto> {
        logger.info("list members: part=$part, maxResults=$maxResults, filterByMemberChannelId=$filterByMemberChannelId, mode=$mode, pageToken=$pageToken, hasAccessToLevel=$hasAccessToLevel")
        return membersApi.listMembers(part, maxResults, filterByMemberChannelId, mode, pageToken, hasAccessToLevel)
    }

    override fun listAllPricingLevels(part: String): Either<Throwable, MembershipLevelListDto> {
        logger.info("list all pricing levels: part=$part")
        return membershipsLevelsApi.listAllPricingLevels(part)
    }

    override fun getChannelInfo(): Either<Throwable, ChannelListDto> {
        logger.info("get channel info")
        return youtubeApi.getChannelInfo()
    }

    override fun getUserInfo(): Either<Throwable, ProfileDto> {
        logger.info("get user info")
        return profileAPi.getUserInfo()
    }

    override fun refreshToken(clientId: String, clientSecret: String, refreshToken: String): Either<Throwable, RefreshTokenDto> {
        logger.info("refresh token")
        return profileAPi.refreshToken(clientId, clientSecret, refreshToken)
    }

    companion object {
        fun create(googleYoutubeApiUrl: String, token: String): GoogleApiDomain {
            return GoogleApiDomain(
                    GoogleYoutubeMembersClient.create(googleYoutubeApiUrl, token),
                    GoogleYoutubeMembershipsLevelsClient.create(googleYoutubeApiUrl, token),
                    GoogleYoutubeDataClient.create(googleYoutubeApiUrl, token),
                    GoogleProfileDataClient.create(googleYoutubeApiUrl, token)
            )
        }
    }
}


