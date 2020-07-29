package com.fourthwall.googlemembersapi.client.domain.model

import com.squareup.moshi.Json

data class ChannelDto (
    @Json(name = "kind")
    val kind: kotlin.String,
    @Json(name = "etag")
    val etag: kotlin.String,
    @Json(name = "id")
    val id: kotlin.String
)