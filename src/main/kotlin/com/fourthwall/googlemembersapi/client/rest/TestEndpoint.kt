package com.fourthwall.googlemembersapi.client.rest

import arrow.core.Either
import com.fourthwall.googlemembersapi.client.domain.GoogleApiDomain
import org.openapitools.client.models.MemberListDto
import org.openapitools.client.models.MembershipLevelListDto
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test", produces = [MediaType.APPLICATION_JSON_VALUE])
class TestEndpoint {

    val googleYoutubeApiUrl = "http://localhost/"

    @GetMapping("/listAllMembers")
    fun listAllMembers(): ResponseEntity<MemberListDto> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl)
        val result = api.listAllMembers("snippet", 10)
        return ResponseEntity.ok(result.getOrThrow())
    }

    @GetMapping("/checkUsersForTheirMemberships")
    fun checkUsersForTheirMemberships(): ResponseEntity<MemberListDto> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl)
        val result = api.checkUsersForTheirMemberships("snippet", "UC5IKUmQWDT6Fh9YL9lMRyHQ")
        return ResponseEntity.ok(result.getOrThrow())
    }

    @GetMapping("/listMembersUpdates")
    fun listMembersUpdates(): ResponseEntity<MemberListDto> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl)
        val result = api.listMembersUpdates("snippet", "updates")
        return ResponseEntity.ok(result.getOrThrow())
    }

    @GetMapping("/listMembers")
    fun listMembers(): ResponseEntity<MemberListDto> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl)
        val result = api.listMembers("snippet", 10,"", "", "", "")
        return ResponseEntity.ok(result.getOrThrow())
    }

    @GetMapping("/listAllPricingLevels")
    fun listAllPricingLevels(): ResponseEntity<MembershipLevelListDto> {
        val api = GoogleApiDomain.create(googleYoutubeApiUrl)
        val result = api.listAllPricingLevels("id")
        return ResponseEntity.ok(result.getOrThrow())
    }

    fun <A : Throwable, B> Either<A, B>.getOrThrow(): B =
            when (this) {
                is Either.Left -> throw this.a
                is Either.Right -> this.b
            }
}
