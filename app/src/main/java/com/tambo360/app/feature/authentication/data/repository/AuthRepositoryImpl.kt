package com.tambo360.app.feature.authentication.data.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.network.safeApiCall
import com.tambo360.app.feature.authentication.data.dto.LoginUserRequest
import com.tambo360.app.feature.authentication.data.dto.RegisterUserRequest
import com.tambo360.app.feature.authentication.data.mapper.LoginUserMapper.toDomain
import com.tambo360.app.feature.authentication.data.mapper.RegisterUserMapper.toDomain
import com.tambo360.app.feature.authentication.data.remote.AuthApi
import com.tambo360.app.feature.authentication.domain.model.LoginUser
import com.tambo360.app.feature.authentication.domain.model.RegisterUser
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun registerUser(request: RegisterUserRequest): NetworkResult<RegisterUser> {
        val result = safeApiCall {
            authApi.registerUser(request)
        }
        return when (result) {
            is NetworkResult.Success -> {
                NetworkResult.Success(result.data.toDomain())
            }

            is NetworkResult.Failed -> {
                NetworkResult.Failed(result.message)
            }
        }
    }

    override suspend fun loginUser(request: LoginUserRequest): NetworkResult<LoginUser> {
        val result = safeApiCall {
            authApi.loginUser(request)
        }
        return when (result) {
            is NetworkResult.Success -> {
                NetworkResult.Success(result.data.toDomain())
            }

            is NetworkResult.Failed -> {
                NetworkResult.Failed(result.message)
            }
        }
    }
}
