package com.kostyarazboynik.company_card_list.datasource

interface SharedPreferencesAppSettings {

    fun isFirstInitializing(): Boolean

    fun setInitialized()
}