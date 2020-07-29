package com.fourthwall.googlemembersapi.client.domain.model

import com.squareup.moshi.Json
import org.openapitools.client.models.PageInfoDto

/**
 * 
 * @param kind 
 * @param etag 
 * @param pageInfo 
 * @param nextPageToken 
 * @param items 
 */

data class ChannelListDto (
    @Json(name = "kind")
    val kind: kotlin.String,
    @Json(name = "etag")
    val etag: kotlin.String,
    @Json(name = "pageInfo")
    val pageInfo: PageInfoDto,
    @Json(name = "nextPageToken")
    val nextPageToken: kotlin.String? = null,
    @Json(name = "items")
    val items: kotlin.Array<ChannelDto>? = null
)
