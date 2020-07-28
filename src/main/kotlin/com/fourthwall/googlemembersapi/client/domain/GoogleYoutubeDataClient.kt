package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.core.right
import arrow.fx.extensions.io.applicative.just
import com.fourthwall.googlemembersapi.client.domain.api.YoutubeApi
import com.fourthwall.googlemembersapi.client.domain.model.ChannelListDto
import com.fourthwall.googlemembersapi.client.support.asIO

class GoogleYoutubeDataClient(private val api: YoutubeApi) : GoogleYoutubeClient(), GoogleYoutubeDataApi {

    override fun getChannelInfo(): Either<Throwable, ChannelListDto> {
        return api.getChannelInfo()
                .asIO { it.right() }
                .redeemWith({ mapException<ChannelListDto>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    companion object {
        fun create(googleYoutubeApiUrl: String, token: String): GoogleYoutubeDataClient {
            val api = createGoogleYoutubeClient(googleYoutubeApiUrl, token, YoutubeApi::class.java)
            return GoogleYoutubeDataClient(api)
        }
    }
}
