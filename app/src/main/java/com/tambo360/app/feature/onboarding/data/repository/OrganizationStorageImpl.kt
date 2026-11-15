package com.tambo360.app.feature.onboarding.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.tambo360.app.feature.onboarding.domain.repository.OrganizationStorage
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class OrganizationStorageImpl @Inject constructor(
    @Named("org_prefs") private val prefs: SharedPreferences
) : OrganizationStorage {

    companion object {
        private const val KEY_ID_ESTABLECIMIENTO = "id_establecimiento"
        private const val KEY_ID_ORGANIZACION = "id_organizacion"
    }

    override fun saveIds(idEstablecimiento: String, idOrganizacion: String) {
        prefs.edit {
            putString(KEY_ID_ESTABLECIMIENTO, idEstablecimiento)
            putString(KEY_ID_ORGANIZACION, idOrganizacion)
        }
    }

    override fun getIdEstablecimiento(): String? =
        prefs.getString(KEY_ID_ESTABLECIMIENTO, null)

    override fun getIdOrganizacion(): String? =
        prefs.getString(KEY_ID_ORGANIZACION, null)

    override fun clear() {
        prefs.edit { clear() }
    }
}
