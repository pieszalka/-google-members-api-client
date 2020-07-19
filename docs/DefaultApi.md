# DefaultApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getYoutubeV3Members**](DefaultApi.md#getYoutubeV3Members) | **GET** /youtube/v3/members | 
[**getYoutubeV3Members_0**](DefaultApi.md#getYoutubeV3Members_0) | **GET** /youtube/v3//members | 
[**getYoutubeV3Members_1**](DefaultApi.md#getYoutubeV3Members_1) | **GET** /youtube/v3///members | 
[**getYoutubeV3Members_2**](DefaultApi.md#getYoutubeV3Members_2) | **GET** /youtube/v3////members | 
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


Configure httpAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getYoutubeV3Members_0"></a>
# **getYoutubeV3Members_0**
> MemberListDto getYoutubeV3Members_0(part, filterByMemberChannelId)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val part : kotlin.String = snippet // kotlin.String | 
val filterByMemberChannelId : kotlin.String = UC_1,UC_2,UC_3 // kotlin.String | 
try {
    val result : MemberListDto = apiInstance.getYoutubeV3Members_0(part, filterByMemberChannelId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getYoutubeV3Members_0")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getYoutubeV3Members_0")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **part** | **kotlin.String**|  |
 **filterByMemberChannelId** | **kotlin.String**|  |

### Return type

[**MemberListDto**](MemberListDto.md)

### Authorization


Configure httpAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getYoutubeV3Members_1"></a>
# **getYoutubeV3Members_1**
> MemberListDto getYoutubeV3Members_1(part, mode)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val part : kotlin.String = snippet // kotlin.String | 
val mode : kotlin.String = all_current // kotlin.String | 
try {
    val result : MemberListDto = apiInstance.getYoutubeV3Members_1(part, mode)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getYoutubeV3Members_1")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getYoutubeV3Members_1")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **part** | **kotlin.String**|  |
 **mode** | **kotlin.String**|  |

### Return type

[**MemberListDto**](MemberListDto.md)

### Authorization


Configure httpAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getYoutubeV3Members_2"></a>
# **getYoutubeV3Members_2**
> MemberListDto getYoutubeV3Members_2(part, maxResults, filterByMemberChannelId, mode, pageToken, hasAccessToLevel)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val part : kotlin.String = snippet // kotlin.String | 
val maxResults : kotlin.Int = 100 // kotlin.Int | 
val filterByMemberChannelId : kotlin.String = UC_1,UC_2,UC_3 // kotlin.String | 
val mode : kotlin.String = all_current // kotlin.String | 
val pageToken : kotlin.String = page token // kotlin.String | 
val hasAccessToLevel : kotlin.String = level ID // kotlin.String | 
try {
    val result : MemberListDto = apiInstance.getYoutubeV3Members_2(part, maxResults, filterByMemberChannelId, mode, pageToken, hasAccessToLevel)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getYoutubeV3Members_2")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getYoutubeV3Members_2")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **part** | **kotlin.String**|  |
 **maxResults** | **kotlin.Int**|  |
 **filterByMemberChannelId** | **kotlin.String**|  |
 **mode** | **kotlin.String**|  |
 **pageToken** | **kotlin.String**|  |
 **hasAccessToLevel** | **kotlin.String**|  |

### Return type

[**MemberListDto**](MemberListDto.md)

### Authorization


Configure httpAuth:
    ApiClient.accessToken = ""

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


Configure httpAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

