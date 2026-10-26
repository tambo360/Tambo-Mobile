package com.tambo360.app.feature.authentication.domain.repository

import com.tambo360.app.core.network.NetworkResult
import com.tambo360.app.feature.authentication.data.dto.RegisterUserRequest
import com.tambo360.app.feature.authentication.domain.model.RegisterUser

interface AuthRepository {

    suspend fun registerUser(request: RegisterUserRequest): NetworkResult<RegisterUser>

}
