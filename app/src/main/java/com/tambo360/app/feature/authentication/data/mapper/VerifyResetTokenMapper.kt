package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.VerifyResetTokenResponse
import com.tambo360.app.feature.authentication.domain.model.VerifyResetToken

object VerifyResetTokenMapper {
    fun VerifyResetTokenResponse.toDomain(): VerifyResetToken {
        return VerifyResetToken(
            success = success,
            message = message
        )
    }
}
