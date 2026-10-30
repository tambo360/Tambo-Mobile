package com.tambo360.app.feature.authentication.domain.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.feature.authentication.data.dto.LoginUserRequest
import com.tambo360.app.feature.authentication.data.dto.RegisterUserRequest
import com.tambo360.app.feature.authentication.data.dto.ResendVerificationRequest
import com.tambo360.app.feature.authentication.data.dto.VerifyEmailRequest
import com.tambo360.app.feature.authentication.domain.model.LoginUser
import com.tambo360.app.feature.authentication.domain.model.RegisterUser
import com.tambo360.app.feature.authentication.domain.model.ResendVerification
import com.tambo360.app.feature.authentication.domain.model.VerifyEmail

interface AuthRepository {

    suspend fun registerUser(request: RegisterUserRequest): NetworkResult<RegisterUser>

    suspend fun loginUser(request: LoginUserRequest): NetworkResult<LoginUser>

    suspend fun verifyEmail(request: VerifyEmailRequest): NetworkResult<VerifyEmail>

    suspend fun resendVerification(request: ResendVerificationRequest): NetworkResult<ResendVerification>

    fun isLoggedIn(): Boolean

    fun setLoggedIn(isLoggedIn: Boolean)

    fun logout()

}
