package org.openapitools.client.apis

import org.openapitools.client.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.MultipartBody

import org.openapitools.client.models.ApiError
import org.openapitools.client.models.Forbidden
import org.openapitools.client.models.InvalidArgument
import org.openapitools.client.models.MemberListDto
import org.openapitools.client.models.MembershipLevelListDto
import org.openapitools.client.models.NotFound
import org.openapitools.client.models.ServiceUnavailable
import org.openapitools.client.models.Unauthorized

interface DefaultApi {
    @GET("/youtube/v3/members")
    fun getYoutubeV3Members(@Query("part") part: kotlin.String, @Query("maxResults") maxResults: kotlin.Int): Call<MemberListDto>

    @GET("/youtube/v3/members")
    fun getYoutubeV3Members_0(@Query("part") part: kotlin.String, @Query("filterByMemberChannelId") filterByMemberChannelId: kotlin.String): Call<MemberListDto>

    @GET("/youtube/v3/members")
    fun getYoutubeV3Members_1(@Query("part") part: kotlin.String, @Query("mode") mode: kotlin.String): Call<MemberListDto>

    @GET("/youtube/v3/members")
    fun getYoutubeV3Members_2(@Query("part") part: kotlin.String, @Query("maxResults") maxResults: kotlin.Int, @Query("filterByMemberChannelId") filterByMemberChannelId: kotlin.String, @Query("mode") mode: kotlin.String, @Query("pageToken") pageToken: kotlin.String, @Query("hasAccessToLevel") hasAccessToLevel: kotlin.String): Call<MemberListDto>

    @GET("/youtube/v3/membershipsLevels")
    fun getYoutubeV3Membershipslevels(@Query("part") part: kotlin.String): Call<MembershipLevelListDto>

}
