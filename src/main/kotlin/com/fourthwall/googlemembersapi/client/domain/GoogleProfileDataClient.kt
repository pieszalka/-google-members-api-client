package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.core.right
import arrow.fx.extensions.io.applicative.just
import com.fourthwall.googlemembersapi.client.domain.api.ProfileApi
import com.fourthwall.googlemembersapi.client.domain.model.ProfileDto
import com.fourthwall.googlemembersapi.client.domain.model.RefreshTokenDto
import com.fourthwall.googlemembersapi.client.support.asIO

class GoogleProfileDataClient(private val api: ProfileApi) : GoogleYoutubeClient(), GoogleProfileDataApi {

    override fun getUserInfo(): Either<Throwable, ProfileDto> {
        return api.getUserInfo()
                .asIO { it.right() }
                .redeemWith({ mapException<ProfileDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    override fun refreshToken(clientId: String, clientSecret: String, refreshToken: String): Either<Throwable, RefreshTokenDto> {
        return api.refreshToken(clientId, clientSecret, refreshToken, "refresh_token")
                .asIO { it.right() }
                .redeemWith({ mapException<RefreshTokenDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }
    companion object {
        fun create(googleYoutubeApiUrl: String, token: String): GoogleProfileDataClient {
            val api = createGoogleYoutubeClient(googleYoutubeApiUrl, token, ProfileApi::class.java)
            return GoogleProfileDataClient(api)
        }
    }

}
