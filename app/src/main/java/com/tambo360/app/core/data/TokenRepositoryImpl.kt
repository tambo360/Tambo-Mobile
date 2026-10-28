package com.tambo360.app.core.data

import android.content.SharedPreferences
import androidx.core.content.edit

class TokenRepositoryImpl(
    private val securePrefs: SharedPreferences
) : TokenRepository {

    companion object {
        private const val KEY_TOKEN = "access_token"
    }

    override fun getToken(): String? = securePrefs.getString(KEY_TOKEN, null)

    override fun saveTokens(accessToken: String) {
        securePrefs.edit {
            putString(KEY_TOKEN, accessToken)
        }
    }

    override fun clearTokens() {
        securePrefs.edit {
            remove(KEY_TOKEN)
        }
    }
}
