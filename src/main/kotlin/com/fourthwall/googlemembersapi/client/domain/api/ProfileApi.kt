package com.fourthwall.googlemembersapi.client.domain.api

import com.fourthwall.googlemembersapi.client.domain.model.ProfileDto
import retrofit2.Call
import retrofit2.http.GET

interface ProfileApi {
    @GET("/oauth2/v3/userinfo")
    fun getUserInfo(): Call<ProfileDto>
}
