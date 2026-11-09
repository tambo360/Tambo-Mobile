package com.tambo360.app.feature.onboarding.data.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.network.safeApiCall
import com.tambo360.app.feature.onboarding.data.dto.request.CreateOrganizationRequest
import com.tambo360.app.feature.onboarding.data.mapper.CreateOrganizationMapper.toDomain
import com.tambo360.app.feature.onboarding.data.remote.OnboardingApi
import com.tambo360.app.feature.onboarding.domain.model.CreateOrganization
import com.tambo360.app.feature.onboarding.domain.repository.OnboardingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingApi: OnboardingApi
) : OnboardingRepository {

    override suspend fun createOrganization(request: CreateOrganizationRequest): NetworkResult<CreateOrganization> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                onboardingApi.createOrganization(request = request)
            }
            when (result) {
                is NetworkResult.Success -> {
                    NetworkResult.Success(result.data.toDomain())
                }
                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }
}
