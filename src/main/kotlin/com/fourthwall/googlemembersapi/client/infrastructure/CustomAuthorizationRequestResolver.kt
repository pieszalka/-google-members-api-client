package com.fourthwall.googlemembersapi.client.infrastructure

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import java.util.*
import javax.servlet.http.HttpServletRequest

class CustomAuthorizationRequestResolver(val repo: ClientRegistrationRepository?, val authorizationRequestBaseUri: String?): OAuth2AuthorizationRequestResolver {

    private var defaultResolver: OAuth2AuthorizationRequestResolver? = DefaultOAuth2AuthorizationRequestResolver(repo, authorizationRequestBaseUri)

    override fun resolve(request: HttpServletRequest?): OAuth2AuthorizationRequest? {
        var req = defaultResolver!!.resolve(request)
        if (req != null) {
            req = customizeAuthorizationRequest(req)
        }
        return req
    }

    override fun resolve(request: HttpServletRequest?, clientRegistrationId: String?): OAuth2AuthorizationRequest? {
        var req = defaultResolver!!.resolve(request, clientRegistrationId)
        if (req != null) {
            req = customizeAuthorizationRequest(req)
        }
        return req
    }


    private fun customizeAuthorizationRequest(req: OAuth2AuthorizationRequest): OAuth2AuthorizationRequest {
        val additionalParameters: MutableMap<String, Any> = LinkedHashMap(req.additionalParameters)
        additionalParameters["access_type"] = "offline"
        return OAuth2AuthorizationRequest.from(req)
                .additionalParameters(additionalParameters)
                .build()
    }
}