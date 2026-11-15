package com.tambo360.app.feature.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.tambo360.app.feature.onboarding.data.repository.OrganizationStorageImpl
import com.tambo360.app.feature.onboarding.domain.repository.OrganizationStorage
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun bindOrganizationStorage(
        impl: OrganizationStorageImpl
    ): OrganizationStorage

    companion object {
        @Provides
        @Singleton
        @Named("org_prefs")
        fun provideOrganizationEncryptedPrefs(
            @ApplicationContext context: Context
        ): SharedPreferences {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            return EncryptedSharedPreferences.create(
                context,
                "secure_organization_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }
}
