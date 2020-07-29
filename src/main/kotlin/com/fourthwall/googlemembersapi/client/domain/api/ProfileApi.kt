package com.fourthwall.googlemembersapi.client.domain.api

import com.fourthwall.googlemembersapi.client.domain.model.ProfileDto
import com.fourthwall.googlemembersapi.client.domain.model.RefreshTokenDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileApi {
    @GET("/oauth2/v3/userinfo")
    fun getUserInfo(): Call<ProfileDto>

    @POST("/oauth2/v4/token")
    fun refreshToken(
            @Query("client_id") clientId: String,
            @Query("client_secret") clientSecret: String,
            @Query("refresh_token") refreshToken: String,
            @Query("grant_type") grantType: String
    ): Call<RefreshTokenDto>
}