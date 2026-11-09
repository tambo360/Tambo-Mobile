package com.tambo360.app.feature.onboarding.data.remote

import com.tambo360.app.feature.onboarding.data.dto.request.CreateOrganizationRequest
import com.tambo360.app.feature.onboarding.data.dto.request.QuestionnaireRequest
import com.tambo360.app.feature.onboarding.data.dto.response.CreateOrganizationResponse
import com.tambo360.app.feature.onboarding.data.dto.response.QuestionnaireResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OnboardingApi {

    @POST("organizacion")
    @Headers("Content-Type: application/json")
    suspend fun createOrganization(
        @Body request: CreateOrganizationRequest
    ): Response<CreateOrganizationResponse>

    @POST("establecimiento/cuestionario")
    @Headers("Content-Type: application/json")
    suspend fun submitQuestionnaire(
        @Body request: QuestionnaireRequest
    ): Response<QuestionnaireResponse>

}