package com.fourthwall.googlemembersapi.client.rest

import arrow.core.Either
import com.fourthwall.googlemembersapi.client.domain.GoogleApiDomain
import com.fourthwall.googlemembersapi.client.domain.GoogleYoutubeClient
import org.openapitools.client.models.MemberListDto
import org.openapitools.client.models.MembershipLevelListDto
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test", produces = [MediaType.APPLICATION_JSON_VALUE])
class TestEndpoint {

    val googleYoutubeApiUrl = "https://www.googleapis.com/"
    val token = ""
    
    @GetMapping("/listAllMembers")
    fun listAllMembers(): ResponseEntity<String> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl, token)
        val result = api.listAllMembers("snippet", 10)
        return result.toResponseEntity()
    }

    @GetMapping("/checkUsersForTheirMemberships")
    fun checkUsersForTheirMemberships(): ResponseEntity<String> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl, token)
        val result = api.checkUsersForTheirMemberships("snippet", "UC5IKUmQWDT6Fh9YL9lMRyHQ")
        return result.toResponseEntity()
    }

    @GetMapping("/listMembersUpdates")
    fun listMembersUpdates(): ResponseEntity<String> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl, token)
        val result = api.listMembersUpdates("snippet", "updates")
        return result.toResponseEntity()
    }

    @GetMapping("/listMembers")
    fun listMembers(): ResponseEntity<String> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl, token)
        val result = api.listMembers("snippet", 10,"", "", "", "")
        return result.toResponseEntity()
    }

    @GetMapping("/listAllPricingLevels")
    fun listAllPricingLevels(): ResponseEntity<String> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl, token)
        val result = api.listAllPricingLevels("id")
        return result.toResponseEntity()
    }

    fun <A : Throwable, B> Either<A, B>.toResponseEntity(): ResponseEntity<String> =
            when (this) {
                is Either.Left -> ResponseEntity.badRequest().body(this.a.message)
                is Either.Right -> ResponseEntity.ok(GoogleYoutubeClient.objectMapper.writeValueAsString(this.b))
            }
}
