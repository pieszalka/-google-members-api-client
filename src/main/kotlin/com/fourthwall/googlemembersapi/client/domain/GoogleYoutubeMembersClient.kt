package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.core.right
import arrow.fx.extensions.io.applicative.just
import com.fourthwall.googlemembersapi.client.support.asIO
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.models.MemberListDto

class GoogleYoutubeMembersClient(private val api: DefaultApi) : GoogleYoutubeClient(), GoogleYoutubeMembersApi {

    override fun listAllMembers(part: String, maxResults: Int): Either<Throwable, MemberListDto> {
        return api.getYoutubeV3Members(part, maxResults)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    override fun checkUsersForTheirMemberships(part: String, filterByMemberChannelId: String): Either<Throwable, MemberListDto> {
        return api.getYoutubeV3Members_0(part, filterByMemberChannelId)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    override fun listMembersUpdates(part: String, mode: String): Either<Throwable, MemberListDto> {
        return api.getYoutubeV3Members_1(part, mode)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    override fun listMembers(part: String, maxResults: Int, filterByMemberChannelId: String, mode: String, pageToken: String, hasAccessToLevel: String): Either<Throwable, MemberListDto> {
        return api.getYoutubeV3Members_2(part, maxResults, filterByMemberChannelId, mode, pageToken, hasAccessToLevel)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    companion object {
        fun create(googleYoutubeApiUrl: String, token: String): GoogleYoutubeMembersClient {
            val api = createGoogleYoutubeClient(googleYoutubeApiUrl, token, DefaultApi::class.java)
            return GoogleYoutubeMembersClient(api)
        }
    }

}
