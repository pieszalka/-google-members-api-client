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

import org.openapitools.client.models.Error

import com.squareup.moshi.Json
/**
 * 
 * @param error 
 */

data class InvalidArgument (
    @Json(name = "error")
    val error: Error
)

