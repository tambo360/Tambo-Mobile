package com.tambo360.app.feature.onboarding.data.mapper

import com.tambo360.app.feature.onboarding.data.dto.response.CreateOrganizationResponse
import com.tambo360.app.feature.onboarding.data.dto.response.OrganizationDataResponse
import com.tambo360.app.feature.onboarding.domain.model.CreateOrganization
import com.tambo360.app.feature.onboarding.domain.model.OrganizationData

object CreateOrganizationMapper {

    fun CreateOrganizationResponse.toDomain(): CreateOrganization {
        return CreateOrganization(
            success = success,
            message = message,
            data = data.toDomain()
        )
    }

    fun OrganizationDataResponse.toDomain(): OrganizationData {
        return OrganizationData(
            idEstablecimiento = idEstablecimiento,
            nombre = nombre,
            idOrganizacion = idOrganizacion,
            localidad = localidad,
            provincia = provincia,
            cuestionarioCompletado = cuestionarioCompletado,
            fechaCreacion = fechaCreacion
        )
    }
}
