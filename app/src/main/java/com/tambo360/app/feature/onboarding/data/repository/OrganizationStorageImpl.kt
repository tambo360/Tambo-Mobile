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
        private const val KEY_NAME = "organization_name"
        private const val KEY_LOCALIDAD = "localidad"
        private const val KEY_PROVINCIA = "provincia"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }

    override fun saveIds(idEst: String, idOrg: String) {
        prefs.edit {
            putString(KEY_ID_ESTABLECIMIENTO, idEst)
            putString(KEY_ID_ORGANIZACION, idOrg)
        }
    }

    override fun getIdEst(): String? =
        prefs.getString(KEY_ID_ESTABLECIMIENTO, null)

    override fun getIdOrg(): String? =
        prefs.getString(KEY_ID_ORGANIZACION, null)

    override fun saveName(name: String) {
        prefs.edit {
            putString(KEY_NAME, name)
        }
    }

    override fun getName(): String? =
        prefs.getString(KEY_NAME, null)
    override fun saveNameUser(nameUser: String) {
        prefs.edit {
            putString(KEY_USER_NAME, nameUser)
        }
    }

    override fun getNameUser(): String? =
        prefs.getString(KEY_USER_NAME, null)

    override fun saveLocation(localidad: String, provincia: String) {
        prefs.edit {
            putString(KEY_LOCALIDAD, localidad)
            putString(KEY_PROVINCIA, provincia)
        }
    }

    override fun getLocalidad(): String? =
        prefs.getString(KEY_LOCALIDAD, null)

    override fun getProvincia(): String? =
        prefs.getString(KEY_PROVINCIA, null)

    override fun setOnboardingCompleted(completed: Boolean) {
        prefs.edit {
            putBoolean(KEY_ONBOARDING_COMPLETED, completed)
        }
    }

    override fun isOnboardingCompleted(): Boolean =
        prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)

    override fun clear() {
        prefs.edit { clear() }
    }
}
