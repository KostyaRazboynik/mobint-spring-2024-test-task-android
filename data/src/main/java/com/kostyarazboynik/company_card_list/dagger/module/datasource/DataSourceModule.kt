package com.kostyarazboynik.company_card_list.dagger.module.datasource

import android.content.Context
import com.kostyarazboynik.company_card_list.datasource.SharedPreferencesAppSettings
import com.kostyarazboynik.company_card_list.datasource.local.SharedPreferencesAppSettingsImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object DataSourceModule {

    @Reusable
    @Provides
    fun provideSharedPreferencesAppSettings(context: Context): SharedPreferencesAppSettings =
        SharedPreferencesAppSettingsImpl(context)

}
