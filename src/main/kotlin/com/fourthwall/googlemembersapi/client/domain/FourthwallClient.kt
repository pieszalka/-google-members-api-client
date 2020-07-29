package com.fourthwall.googlemembersapi.client.domain

import arrow.core.Either
import arrow.core.right
import arrow.fx.extensions.io.applicative.just
import com.fourthwall.googlemembersapi.client.domain.api.FourthwallApi
import com.fourthwall.googlemembersapi.client.domain.model.FourthwallYoutubeSubscriptionDto
import com.fourthwall.googlemembersapi.client.support.asIO
import org.openapitools.client.apis.DefaultApi
import org.openapitools.client.models.MemberListDto

class FourthwallClient(private val api: FourthwallApi) : GoogleYoutubeClient(), FourthwallClientApi {

    override fun sendSubscription(subscription: FourthwallYoutubeSubscriptionDto): Either<Throwable, Unit> {
        return api.sendSubscription(subscription)
                .asIO { it.right() }
                .redeemWith({ mapException<Unit>(it) }, { it.just().map {
                    if (it.body() != null) {
                        Either.right(it.body()!!)
                    } else {
                        mapError(it)
                    }
                } })
                .unsafeRunSync()
    }

    companion object {
        fun create(fourthwallApiUrl: String): FourthwallClient {
            val api = createFourthwallClient(fourthwallApiUrl, FourthwallApi::class.java)
            return FourthwallClient(api)
        }
    }

}
