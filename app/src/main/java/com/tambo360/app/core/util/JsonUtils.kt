package com.tambo360.app.core.util

import org.json.JSONArray
import org.json.JSONObject

object JsonUtils {

    fun getErrorMessage(rawMessage: String): String {
        val cleanMessage = rawMessage.trim()
        return try {
            when {
                cleanMessage.startsWith("[") -> {
                    val array = JSONArray(cleanMessage)
                    if (array.length() > 0) array.getString(0) else rawMessage
                }

                cleanMessage.startsWith("{") -> {
                    val jsonObject = JSONObject(cleanMessage)
                    var errorData = jsonObject.opt("message")
                    if (errorData == null || errorData == JSONObject.NULL) {
                        errorData = jsonObject.opt("error")
                    }

                    when (errorData) {
                        is JSONArray -> {
                            if (errorData.length() > 0) errorData.getString(0) else rawMessage
                        }
                        is String -> {
                            errorData
                        }
                        else -> rawMessage
                    }
                }

                else -> rawMessage
            }
        } catch (e: Exception) {
            e.printStackTrace()
            rawMessage
        }
    }

}
