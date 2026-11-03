package com.tambo360.app.feature.authentication.data.remote

import com.tambo360.app.feature.authentication.data.dto.ForgotPasswordRequest
import com.tambo360.app.feature.authentication.data.dto.ForgotPasswordResponse
import com.tambo360.app.feature.authentication.data.dto.LoginUserRequest
import com.tambo360.app.feature.authentication.data.dto.LoginUserResponse
import com.tambo360.app.feature.authentication.data.dto.RegisterUserRequest
import com.tambo360.app.feature.authentication.data.dto.RegisterUserResponse
import com.tambo360.app.feature.authentication.data.dto.ResendVerificationRequest
import com.tambo360.app.feature.authentication.data.dto.ResendVerificationResponse
import com.tambo360.app.feature.authentication.data.dto.ResetPasswordRequest
import com.tambo360.app.feature.authentication.data.dto.ResetPasswordResponse
import com.tambo360.app.feature.authentication.data.dto.VerifyEmailRequest
import com.tambo360.app.feature.authentication.data.dto.VerifyEmailResponse
import com.tambo360.app.feature.authentication.data.dto.VerifyResetTokenRequest
import com.tambo360.app.feature.authentication.data.dto.VerifyResetTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/crear-cuenta")
    @Headers("Content-Type: application/json")
    suspend fun registerUser(
        @Body request: RegisterUserRequest
    ): Response<RegisterUserResponse>

    @POST("auth/iniciar-sesion")
    @Headers("Content-Type: application/json")
    suspend fun loginUser(
        @Body request: LoginUserRequest
    ): Response<LoginUserResponse>

    @POST("auth/verificar-email")
    @Headers("Content-Type: application/json")
    suspend fun verifyEmail(
        @Body request: VerifyEmailRequest
    ): Response<VerifyEmailResponse>

    @POST("auth/reenviar-verificacion")
    @Headers("Content-Type: application/json")
    suspend fun resendVerification(
        @Body request: ResendVerificationRequest
    ): Response<ResendVerificationResponse>

    @POST("auth/contrasena-olvidada")
    @Headers("Content-Type: application/json")
    suspend fun forgotPassword(
        @Body request: ForgotPasswordRequest
    ): Response<ForgotPasswordResponse>

    @POST("auth/verificar-restablecer-contrasena")
    @Headers("Content-Type: application/json")
    suspend fun verifyResetToken(
        @Body request: VerifyResetTokenRequest
    ): Response<VerifyResetTokenResponse>

    @POST("auth/restablecer-contrasena")
    @Headers("Content-Type: application/json")
    suspend fun resetPassword(
        @Body request: ResetPasswordRequest
    ): Response<ResetPasswordResponse>

}
