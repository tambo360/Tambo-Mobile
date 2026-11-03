package com.tambo360.app.feature.authentication.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.core.network.safeApiCall
import com.tambo360.app.feature.authentication.data.dto.ForgotPasswordRequest
import com.tambo360.app.feature.authentication.data.dto.LoginUserRequest
import com.tambo360.app.feature.authentication.data.dto.RegisterUserRequest
import com.tambo360.app.feature.authentication.data.dto.ResendVerificationRequest
import com.tambo360.app.feature.authentication.data.dto.ResetPasswordRequest
import com.tambo360.app.feature.authentication.data.dto.VerifyEmailRequest
import com.tambo360.app.feature.authentication.data.dto.VerifyResetTokenRequest
import com.tambo360.app.feature.authentication.data.mapper.ForgotPasswordMapper.toDomain
import com.tambo360.app.feature.authentication.data.mapper.LoginUserMapper.toDomain
import com.tambo360.app.feature.authentication.data.mapper.RegisterUserMapper.toDomain
import com.tambo360.app.feature.authentication.data.mapper.ResendVerificationMapper.toDomain
import com.tambo360.app.feature.authentication.data.mapper.ResetPasswordMapper.toDomain
import com.tambo360.app.feature.authentication.data.mapper.VerifyEmailMapper.toDomain
import com.tambo360.app.feature.authentication.data.mapper.VerifyResetTokenMapper.toDomain
import com.tambo360.app.feature.authentication.data.remote.AuthApi
import com.tambo360.app.feature.authentication.domain.model.ForgotPassword
import com.tambo360.app.feature.authentication.domain.model.LoginUser
import com.tambo360.app.feature.authentication.domain.model.RegisterUser
import com.tambo360.app.feature.authentication.domain.model.ResendVerification
import com.tambo360.app.feature.authentication.domain.model.ResetPassword
import com.tambo360.app.feature.authentication.domain.model.VerifyEmail
import com.tambo360.app.feature.authentication.domain.model.VerifyResetToken
import com.tambo360.app.feature.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    override suspend fun registerUser(request: RegisterUserRequest): NetworkResult<RegisterUser> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                authApi.registerUser(request)
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

    override suspend fun loginUser(request: LoginUserRequest): NetworkResult<LoginUser> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                authApi.loginUser(request)
            }
            when (result) {
                is NetworkResult.Success -> {
                    setLoggedIn(true)
                    NetworkResult.Success(result.data.toDomain())
                }

                is NetworkResult.Failed -> {
                    NetworkResult.Failed(result.message)
                }
            }
        }
    }

    override suspend fun verifyEmail(request: VerifyEmailRequest): NetworkResult<VerifyEmail> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                authApi.verifyEmail(request)
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

    override suspend fun resendVerification(request: ResendVerificationRequest): NetworkResult<ResendVerification> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                authApi.resendVerification(request)
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

    override suspend fun forgotPassword(request: ForgotPasswordRequest): NetworkResult<ForgotPassword> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                authApi.forgotPassword(request)
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

    override suspend fun verifyResetToken(request: VerifyResetTokenRequest): NetworkResult<VerifyResetToken> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                authApi.verifyResetToken(request)
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

    override suspend fun resetPassword(request: ResetPasswordRequest): NetworkResult<ResetPassword> {
        return withContext(Dispatchers.IO) {
            val result = safeApiCall {
                authApi.resetPassword(request)
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

    override fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    override fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_IS_LOGGED_IN, isLoggedIn) }
    }

    override fun logout() {
        setLoggedIn(false)
    }
}
