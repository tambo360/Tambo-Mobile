package com.tambo360.app.core.network

import com.tambo360.app.core.data.TokenRepository
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import javax.inject.Inject

class TokenCookieJar @Inject constructor(
    private val tokenRepository: TokenRepository
) : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val tokenCookie = cookies.find { it.name == "token" }
        if (tokenCookie != null) {
            tokenRepository.saveTokens(tokenCookie.value)
        }
    }
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val savedToken = tokenRepository.getToken()

        return if (savedToken != null) {
            val cookie = Cookie.Builder()
                .name("token")
                .value(savedToken)
                .domain(url.host)
                .build()

            listOf(cookie)
        } else {
            emptyList()
        }
    }
}
