package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.ResendVerificationResponse
import com.tambo360.app.feature.authentication.domain.model.ResendVerification

object ResendVerificationMapper {

    fun ResendVerificationResponse.toDomain(): ResendVerification {
        return ResendVerification(
            success = success,
            message = message
        )
    }
}
