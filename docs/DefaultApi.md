# DefaultApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getYoutubeV3Members**](DefaultApi.md#getYoutubeV3Members) | **GET** /youtube/v3/members | 
[**getYoutubeV3Membershipslevels**](DefaultApi.md#getYoutubeV3Membershipslevels) | **GET** /youtube/v3/membershipsLevels | 


<a name="getYoutubeV3Members"></a>
# **getYoutubeV3Members**
> MemberListDto getYoutubeV3Members(part, maxResults)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val part : kotlin.String = snippet // kotlin.String | 
val maxResults : kotlin.Int = 100 // kotlin.Int | 
try {
    val result : MemberListDto = apiInstance.getYoutubeV3Members(part, maxResults)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getYoutubeV3Members")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getYoutubeV3Members")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **part** | **kotlin.String**|  |
 **maxResults** | **kotlin.Int**|  |

### Return type

[**MemberListDto**](MemberListDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getYoutubeV3Membershipslevels"></a>
# **getYoutubeV3Membershipslevels**
> MembershipLevelListDto getYoutubeV3Membershipslevels(part)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val part : kotlin.String = id // kotlin.String | 
try {
    val result : MembershipLevelListDto = apiInstance.getYoutubeV3Membershipslevels(part)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getYoutubeV3Membershipslevels")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getYoutubeV3Membershipslevels")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **part** | **kotlin.String**|  |

### Return type

[**MembershipLevelListDto**](MembershipLevelListDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

