package io.github.dwikiriyadi.sharedpreferences.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EncryptedPreferences(context: Context, prefsName: String) :
    BasePreferencesContract {

    override val sharedPref: SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            prefsName,
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

}