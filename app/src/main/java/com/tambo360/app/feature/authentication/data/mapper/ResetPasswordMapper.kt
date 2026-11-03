package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.ResetPasswordResponse
import com.tambo360.app.feature.authentication.domain.model.ResetPassword

object ResetPasswordMapper {
    fun ResetPasswordResponse.toDomain(): ResetPassword {
        return ResetPassword(
            success = success,
            message = message
        )
    }
}
