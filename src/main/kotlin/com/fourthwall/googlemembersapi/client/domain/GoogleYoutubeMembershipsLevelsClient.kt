package com.fourthwall.manufacturer.printful

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
                .redeemWith({ mapException<MembershipLevelListDto>(it) }, { it.just().map { Either.right(it.body()!!) } })
                .unsafeRunSync()
    }

    companion object {
        fun create(googleYoutubeApiUrl: String): GoogleYoutubeMembershipsLevelsClient {
            val api = createGoogleYoutubeRetrofitClient(googleYoutubeApiUrl)
                    .create(DefaultApi::class.java)
            return GoogleYoutubeMembershipsLevelsClient(api)
        }
    }

}
