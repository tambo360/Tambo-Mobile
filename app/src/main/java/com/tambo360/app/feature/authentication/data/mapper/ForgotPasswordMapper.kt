package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.ForgotPasswordResponse
import com.tambo360.app.feature.authentication.domain.model.ForgotPassword

object ForgotPasswordMapper {
    fun ForgotPasswordResponse.toDomain(): ForgotPassword {
        return ForgotPassword(
            success = success,
            message = message
        )
    }
}
