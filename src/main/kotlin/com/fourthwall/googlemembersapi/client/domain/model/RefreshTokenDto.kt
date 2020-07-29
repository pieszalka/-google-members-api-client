package com.fourthwall.googlemembersapi.client.domain.model

import com.squareup.moshi.Json

data class RefreshTokenDto (
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "expires_in")
    val expiresIn: Int,
    @Json(name = "scope")
    val scope: String,
    @Json(name = "token_type")
    val tokenType: String
)
