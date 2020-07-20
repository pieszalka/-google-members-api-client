package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import org.openapitools.client.models.MemberListDto
import org.openapitools.client.models.MembershipLevelListDto
import org.slf4j.LoggerFactory

interface GoogleApi : GoogleYoutubeMembersApi, GoogleYoutubeMembershipsLevelsApi

interface GoogleYoutubeMembersApi {

    fun listAllMembers(part: String, maxResults: Int): Either<Throwable, MemberListDto>
    fun checkUsersForTheirMemberships(part: String, filterByMemberChannelId: String): Either<Throwable, MemberListDto>
    fun listMembersUpdates(part: String, mode: String): Either<Throwable, MemberListDto>
    fun listMembers(part: String, maxResults: Int, filterByMemberChannelId: String, mode: String, pageToken: String, hasAccessToLevel: String): Either<Throwable, MemberListDto>
}

interface GoogleYoutubeMembershipsLevelsApi {

    fun listAllPricingLevels(part: String): Either<Throwable, MembershipLevelListDto>
}

open class GoogleApiDomain(
        private val membersApi: GoogleYoutubeMembersApi,
        private val membershipsLevelsApi: GoogleYoutubeMembershipsLevelsApi
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

    companion object {
        fun create(googleYoutubeApiUrl: String, token: String): GoogleApiDomain {
            return GoogleApiDomain(
                    GoogleYoutubeMembersClient.create(googleYoutubeApiUrl, token),
                    GoogleYoutubeMembershipsLevelsClient.create(googleYoutubeApiUrl, token)
            )
        }
    }
}


