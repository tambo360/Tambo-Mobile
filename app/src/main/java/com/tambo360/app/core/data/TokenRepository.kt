package com.tambo360.app.core.data

interface TokenRepository {
    fun getToken(): String?
    fun saveTokens(accessToken: String)
    fun clearTokens()
}
