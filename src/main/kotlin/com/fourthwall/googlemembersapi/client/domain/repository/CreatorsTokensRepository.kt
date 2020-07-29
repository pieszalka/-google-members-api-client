package com.fourthwall.googlemembersapi.client.domain.repository

import org.springframework.stereotype.Repository

@Repository
class CreatorsTokensRepository {

    private val tokens: MutableList<Token> = mutableListOf(
            Token(
                    accessToken = "ya29.a0AfH6SMAmO1J2ekDvLqhIx_ZLGKBtiQ1Qs47g-LkGVzRve_SiupRLH_bZMZlKECD9ZQeO7NxNSimjK879qsi6-42qY-y7fBRmj8hIrR9-efJvMYNTPN6QMx0gUjKTaIjJ9hFys2LrjP3eDVRVwk2Fwcvm3GfrRBdCU5w",
                    refreshToken = "1//0cclaiYeUhr9QCgYIARAAGAwSNwF-L9IrWFFLqAIQ8gNnX0KWPiGVgojcushiPlbvl-sOrzc792ih3zTGdntjwuxEEGdBGpS30Us"
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