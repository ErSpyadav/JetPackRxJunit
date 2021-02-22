package com.example.jetpackwithjunit.di.module

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferenceConnectorModule {
    private val PREF_NAME = "SCONNECT_PREFS"

    // Create or retrieve the Master Key for encryption/decryption
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    companion object {
        const val USER_MOBILE = "mobile"
        const val USER_PASSWORD = "password"

    }

    @Provides
    @Singleton
    fun providePreferenceConnector(application: Application): SharedPreferences {
        // Initialize/open an instance of EncryptedSharedPreferences
        return EncryptedSharedPreferences.create(PREF_NAME, masterKeyAlias, application,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }
}