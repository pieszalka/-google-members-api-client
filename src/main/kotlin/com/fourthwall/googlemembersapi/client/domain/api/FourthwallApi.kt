package com.fourthwall.googlemembersapi.client.domain.api

import com.fourthwall.googlemembersapi.client.domain.model.FourthwallYoutubeSubscriptionDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FourthwallApi {
    @POST("/api/kudos/test/youtubepocty")
    fun sendSubscription(
            @Body subscription: FourthwallYoutubeSubscriptionDto
    ): Call<Unit>
}

