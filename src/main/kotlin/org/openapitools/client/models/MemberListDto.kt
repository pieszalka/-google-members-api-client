/**
* Google Members Api
* No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
*
* The version of the OpenAPI document: 0.0.1
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools.client.models

import org.openapitools.client.models.MemberDto
import org.openapitools.client.models.PageInfoDto

import com.squareup.moshi.Json
/**
 * 
 * @param kind 
 * @param etag 
 * @param nextPageToken 
 * @param pageInfo 
 * @param items 
 */

data class MemberListDto (
    @Json(name = "kind")
    val kind: kotlin.String,
    @Json(name = "etag")
    val etag: kotlin.String,
    @Json(name = "nextPageToken")
    val nextPageToken: kotlin.String,
    @Json(name = "pageInfo")
    val pageInfo: PageInfoDto,
    @Json(name = "items")
    val items: kotlin.Array<MemberDto>? = null
)

