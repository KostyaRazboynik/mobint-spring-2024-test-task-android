package com.kostyarazboynik.company_card_list.datasource.local


import android.content.Context
import com.kostyarazboynik.company_card_list.datasource.SharedPreferencesAppSettings
import javax.inject.Inject

class SharedPreferencesAppSettingsImpl @Inject constructor(
    context: Context,
) : SharedPreferencesAppSettings {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    init {
        if (!sharedPreferences.contains(SHARED_PREFERENCES_FIRST_INITIALIZING)) {
            editor.putBoolean(SHARED_PREFERENCES_FIRST_INITIALIZING, true)
                .apply()
        }
    }

    override fun isFirstInitializing() =
        sharedPreferences.getBoolean(SHARED_PREFERENCES_FIRST_INITIALIZING, false)

    override fun setInitialized() =
        editor.putBoolean(SHARED_PREFERENCES_FIRST_INITIALIZING, false).apply()

    private companion object {
        private const val SHARED_PREFERENCES_NAME = "settings"
        private const val SHARED_PREFERENCES_FIRST_INITIALIZING = "first_initializing"
    }
}
