package com.fourthwall.googlemembersapi.client.domain.api

import com.fourthwall.googlemembersapi.client.domain.model.ChannelListDto
import retrofit2.Call
import retrofit2.http.GET

interface YoutubeApi {
    @GET("/youtube/v3/channels?mine=true")
    fun getChannelInfo(): Call<ChannelListDto>
}
