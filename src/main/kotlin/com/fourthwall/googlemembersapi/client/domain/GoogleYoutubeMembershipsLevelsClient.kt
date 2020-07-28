package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.core.right
import arrow.fx.extensions.io.applicative.just
import com.fourthwall.googlemembersapi.client.support.asIO
import com.fourthwall.googlemembersapi.client.domain.GoogleYoutubeClient
import com.fourthwall.googlemembersapi.client.domain.GoogleYoutubeMembershipsLevelsApi
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.models.MembershipLevelListDto

class GoogleYoutubeMembershipsLevelsClient(private val api: DefaultApi) : GoogleYoutubeClient(), GoogleYoutubeMembershipsLevelsApi {

    override fun listAllPricingLevels(part: String): Either<Throwable, MembershipLevelListDto> {
        return api.getYoutubeV3Membershipslevels(part)
                .asIO { it.right() }
                .redeemWith({ mapException<MembershipLevelListDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    companion object {
        fun create(googleYoutubeApiUrl: String, token: String): GoogleYoutubeMembershipsLevelsClient {
            val api = createGoogleYoutubeClient(googleYoutubeApiUrl, token, DefaultApi::class.java)
            return GoogleYoutubeMembershipsLevelsClient(api)
        }
    }

}
