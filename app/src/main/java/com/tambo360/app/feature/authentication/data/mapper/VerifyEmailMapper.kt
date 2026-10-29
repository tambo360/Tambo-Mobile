package com.tambo360.app.feature.authentication.data.mapper

import com.tambo360.app.feature.authentication.data.dto.VerifyEmailDataResponse
import com.tambo360.app.feature.authentication.data.dto.VerifyEmailResponse
import com.tambo360.app.feature.authentication.data.mapper.LoginUserMapper.toDomain
import com.tambo360.app.feature.authentication.domain.model.VerifyEmail
import com.tambo360.app.feature.authentication.domain.model.VerifyEmailData

object VerifyEmailMapper {

    fun VerifyEmailResponse.toDomain(): VerifyEmail {
        return VerifyEmail(
            success = success,
            message = message,
            data = data.toDomain()
        )
    }

    fun VerifyEmailDataResponse.toDomain(): VerifyEmailData {
        return VerifyEmailData(
            user = user.toDomain()
        )
    }
}
