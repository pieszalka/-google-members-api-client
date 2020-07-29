package com.fourthwall.googlemembersapi.client.rest

import arrow.core.Either
import com.fourthwall.googlemembersapi.client.domain.GoogleApiDomain
import com.fourthwall.googlemembersapi.client.domain.model.FourthwallYoutubeSubscriptionDto
import com.fourthwall.googlemembersapi.client.domain.repository.*
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*
import java.util.function.Consumer


@Controller
class LoginEndpoint(
        val membersSubscriptionsRepository: MembersSubscriptionsRepository,
        val channelMembershipsRepository: ChannelMembershipsRepository,
        val tokensRepository: CreatorsTokensRepository,
        val clientRegistrationRepository: ClientRegistrationRepository,
        val authorizedClientService: OAuth2AuthorizedClientService
) {
    val authorizationRequestBaseUri = "oauth2/authorize-client"
    val googleYoutubeApiUrl = "https://www.googleapis.com/"
    val fourthwallApiUrl = "https://fourthwall.com/"

    @GetMapping("/login")
    fun getLoginCreatorPage(model: Model): String {
        val clientRegistrations =  clientRegistrationRepository as Iterable<ClientRegistration>
        val oauth2AuthenticationUrls: HashMap<String, String> = HashMap()
        clientRegistrations.forEach(Consumer { registration: ClientRegistration ->
            oauth2AuthenticationUrls[registration.clientName] = authorizationRequestBaseUri + "/" + registration.registrationId
        })
        model.addAttribute("urls", oauth2AuthenticationUrls)
        return "login"
    }

    @GetMapping("/loginSuccess")
    fun getLoginInfo(model: Model, authentication: OAuth2AuthenticationToken): String? {
        val client: OAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(authentication.authorizedClientRegistrationId, authentication.name)
        val token = client.accessToken.tokenValue
        val api = GoogleApiDomain.create(googleYoutubeApiUrl, fourthwallApiUrl, token)

        val members = api.listAllMembers("snippet", 100).toModel()
        if (members != null) {
            //creator flow
            val profile = api.getUserInfo().toModel()!!
            members.items
                    ?.map { Pair(it.snippet.creatorChannelId, it.snippet.memberDetails.channelId) }
                    ?.groupBy { it.first }
                    ?.forEach { (creatorChannelId, channels) -> channelMembershipsRepository.saveMemberships(creatorChannelId, channels.map { it.second }) }
            tokensRepository.saveToken(Token(token, client.refreshToken?.tokenValue ?: ""))

            model.addAttribute("name", profile.name)
            model.addAttribute("email", profile.email)
            model.addAttribute("identity", profile.name)

            return "loginSuccessCreator"
        } else {
            //supporter flow
            val profile = api.getUserInfo().toModel()!!
            val channels = api.getChannelInfo().toModel()
            val profileChannels = channels?.items?.map { it.id }?.toList() ?: emptyList()
            val memberships = channelMembershipsRepository.getSubscribedChannels(profileChannels)

            model.addAttribute("name", profile.name)
            model.addAttribute("email", profile.email)
            model.addAttribute("memberships", memberships.joinToString("\n"))
            model.addAttribute("identity", profile.name)

            return "loginSuccess"
        }
    }

    @PostMapping("/saveEmail")
    fun saveEmail(
            @RequestParam("identity") identity: String,
            @RequestParam("email") email: String,
            @RequestParam("memberships") memberships: String,
            @RequestParam("message") message: String,
            model: Model,
            authentication: OAuth2AuthenticationToken
            ): String? {
        memberships.split("\n").forEach { membership ->
            membersSubscriptionsRepository.saveSubscription(Subscription(identity, email, membership))
        }
        val client: OAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(authentication.authorizedClientRegistrationId, authentication.name)
        val token = client.accessToken.tokenValue
        val api = GoogleApiDomain.create(googleYoutubeApiUrl, fourthwallApiUrl, token)
        api.sendSubscription(FourthwallYoutubeSubscriptionDto(email, identity, message))

        model.addAttribute("identity", identity)
        model.addAttribute("email", email)
        model.addAttribute("subscriptions", membersSubscriptionsRepository.getUserSubscriptions(identity).toString())
        return "saveSuccess"
    }

    fun <A : Throwable, B> Either<A, B>.toModel(): B? =
            when (this) {
                is Either.Left -> {
                    println("Error: " + a.message)
                    null
                }
                is Either.Right -> b
            }
}
