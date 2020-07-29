package com.fourthwall.googlemembersapi.client.domain.repository

import org.springframework.stereotype.Repository

@Repository
class CreatorsTokensRepository {

    private val tokens: MutableList<Token> = mutableListOf(
            Token(
                    accessToken = "ya29.a0AfH6SMCJRMs4fCTyhWD1iIs410USXSWMCEigXy4KSJozFf4TWYMuJpJpqFccCmfmKZYP5Y3nX-Vqm-6k4wYfk6BSjBV6JMuElbLVEBlf1xpbdHWhbKSFJAK9OYKolM5SZzNidP2oRdWh53zit1Z5P6N6nVHqpzJWpUE",
                    refreshToken = "1//0clpQ0F39dfnTCgYIARAAGAwSNwF-L9IrPxM-PR6EzT0K3AUxQja_OECb5EQOMaBnt74Q3cytmG4QMWTApqTHlBB7v0WebzmcV98"
            )
    )

    fun saveToken(token: Token): Unit {
        tokens.add(token)
    }

    fun getAllTokens(): List<Token> {
        return tokens.toList()
    }

    fun removeToken(token: Token): Unit {
        tokens.remove(token)
    }

}

data class Token(val accessToken: String, val refreshToken: String)