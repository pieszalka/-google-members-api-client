# org.openapitools.client - Kotlin client library for Google Members Api

## Requires

* Kotlin 1.3.41
* Gradle 4.9

## Build

First, create the gradle wrapper script:

```
gradle wrapper
```

Then, run:

```
./gradlew check assemble
```

This runs all tests and packages the library.

## Features/Implementation Notes

* Supports JSON inputs/outputs, File inputs, and Form inputs.
* Supports collection formats for query parameters: csv, tsv, ssv, pipes.
* Some Kotlin and Java types are fully qualified to avoid conflicts with types defined in OpenAPI definitions.
* Implementation of ApiClient is intended to reduce method counts, specifically to benefit Android targets.

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**getYoutubeV3Members**](docs/DefaultApi.md#getyoutubev3members) | **GET** /youtube/v3/members | 
*DefaultApi* | [**getYoutubeV3Membershipslevels**](docs/DefaultApi.md#getyoutubev3membershipslevels) | **GET** /youtube/v3/membershipsLevels | 


<a name="documentation-for-models"></a>
## Documentation for Models

 - [org.openapitools.client.models.Error](docs/Error.md)
 - [org.openapitools.client.models.ErrorDetails](docs/ErrorDetails.md)
 - [org.openapitools.client.models.Forbidden](docs/Forbidden.md)
 - [org.openapitools.client.models.InvalidArgument](docs/InvalidArgument.md)
 - [org.openapitools.client.models.MemberDetailsDto](docs/MemberDetailsDto.md)
 - [org.openapitools.client.models.MemberDto](docs/MemberDto.md)
 - [org.openapitools.client.models.MemberListDto](docs/MemberListDto.md)
 - [org.openapitools.client.models.MemberSnippetDto](docs/MemberSnippetDto.md)
 - [org.openapitools.client.models.MembershipLevelDetailsDto](docs/MembershipLevelDetailsDto.md)
 - [org.openapitools.client.models.MembershipLevelDto](docs/MembershipLevelDto.md)
 - [org.openapitools.client.models.MembershipLevelListDto](docs/MembershipLevelListDto.md)
 - [org.openapitools.client.models.MembershipLevelSnippetDto](docs/MembershipLevelSnippetDto.md)
 - [org.openapitools.client.models.MembershipsDetailsDto](docs/MembershipsDetailsDto.md)
 - [org.openapitools.client.models.MembershipsDurationAtLevelDto](docs/MembershipsDurationAtLevelDto.md)
 - [org.openapitools.client.models.MembershipsDurationDto](docs/MembershipsDurationDto.md)
 - [org.openapitools.client.models.NotFound](docs/NotFound.md)
 - [org.openapitools.client.models.PageInfoDto](docs/PageInfoDto.md)
 - [org.openapitools.client.models.ServiceUnavailable](docs/ServiceUnavailable.md)
 - [org.openapitools.client.models.Unauthorized](docs/Unauthorized.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
