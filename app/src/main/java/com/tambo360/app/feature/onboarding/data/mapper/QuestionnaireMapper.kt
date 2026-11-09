package com.tambo360.app.feature.onboarding.data.mapper

import com.tambo360.app.feature.onboarding.data.dto.response.QuestionnaireResponse
import com.tambo360.app.feature.onboarding.domain.model.QuestionnaireResult

object QuestionnaireMapper {

    fun QuestionnaireResponse.toDomain(): QuestionnaireResult {
        return QuestionnaireResult(
            success = success,
            message = message,
            status = data.status
        )
    }
}
