package com.kostyarazboynik.company_card_list.usecase

import com.kostyarazboynik.company_card_list.datasource.SharedPreferencesAppSettings

class FirstInitializingUseCase(
    private val sharedPreferencesAppSettings: SharedPreferencesAppSettings
) {

    fun isFirstInitializing() = sharedPreferencesAppSettings.isFirstInitializing()

    fun setInitialized() = sharedPreferencesAppSettings.setInitialized()
}