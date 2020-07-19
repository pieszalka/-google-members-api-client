package com.fourthwall.manufacturer.printful

import arrow.core.Either
import arrow.core.right
import arrow.fx.extensions.io.applicative.just
import com.fourthwall.googlemembersapi.client.support.asIO
import com.fourthwall.googlemembersapi.client.domain.GoogleYoutubeClient
import com.fourthwall.googlemembersapi.client.domain.GoogleYoutubeMembersApi
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.models.MemberListDto

class GoogleYoutubeMembersClient(private val api: DefaultApi) : GoogleYoutubeClient(), GoogleYoutubeMembersApi {

    override fun listAllMembers(part: String, maxResults: Int): Either<Throwable, MemberListDto> {
        return api.getYoutubeV3Members(part, maxResults)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map { Either.right(it.body()!!) } })
                .unsafeRunSync()
    }

    override fun checkUsersForTheirMemberships(part: String, filterByMemberChannelId: String): Either<Throwable, MemberListDto> {
        /*return api.checkUsersForTheirMemberships(part, filterByMemberChannelId)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map { Either.right(it.body()!!) } })
                .unsafeRunSync()*/
        TODO("not implemented")
    }

    override fun listMembersUpdates(part: String, mode: String): Either<Throwable, MemberListDto> {
        /*return api.listMembersUpdates(part, mode)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map { Either.right(it.body()!!) } })
                .unsafeRunSync()*/
        TODO("not implemented")
    }

    override fun listMembers(part: String, maxResults: Int, filterByMemberChannelId: String, mode: String, pageToken: String, hasAccessToLevel: String): Either<Throwable, MemberListDto> {
        /*return api.listMembers(part, maxResults, filterByMemberChannelId, mode, pageToken, hasAccessToLevel)
                .asIO { it.right() }
                .redeemWith({ mapException<MemberListDto>(it) }, { it.just().map { Either.right(it.body()!!) } })
                .unsafeRunSync()*/
        TODO("not implemented")
    }

    companion object {
        fun create(googleYoutubeApiUrl: String): GoogleYoutubeMembersClient {
            val api = createGoogleYoutubeRetrofitClient(googleYoutubeApiUrl)
                    .create(DefaultApi::class.java)
            return GoogleYoutubeMembersClient(api)
        }
    }

}
