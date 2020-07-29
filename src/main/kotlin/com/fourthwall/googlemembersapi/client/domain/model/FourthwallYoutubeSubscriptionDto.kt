package com.fourthwall.googlemembersapi.client.domain.model

import com.squareup.moshi.Json

data class FourthwallYoutubeSubscriptionDto (
    @Json(name = "email")
    val email: kotlin.String,
    @Json(name = "name")
    val name: kotlin.String,
    @Json(name = "message")
    val message: kotlin.String
)