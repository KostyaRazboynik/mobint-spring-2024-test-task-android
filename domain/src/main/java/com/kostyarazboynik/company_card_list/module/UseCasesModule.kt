package com.kostyarazboynik.company_card_list.module

import com.kostyarazboynik.company_card_list.datasource.SharedPreferencesAppSettings
import com.kostyarazboynik.company_card_list.repository.CompaniesListLocalRepository
import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import com.kostyarazboynik.company_card_list.usecase.FirstInitializingUseCase
import com.kostyarazboynik.company_card_list.usecase.GetLocalCompaniesListUseCase
import com.kostyarazboynik.company_card_list.usecase.GetMergedCompaniesListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetCompaniesListUseCase(
        companiesListRemoteRepository: CompaniesListRemoteRepository,
        companiesListLocalRepository: CompaniesListLocalRepository,
    ): GetMergedCompaniesListUseCase {
        return GetMergedCompaniesListUseCase(
            companiesListRemoteRepository,
            companiesListLocalRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetLocalCompaniesListUseCase(
        companiesListLocalRepository: CompaniesListLocalRepository,
    ): GetLocalCompaniesListUseCase {
        return GetLocalCompaniesListUseCase(
            companiesListLocalRepository,
        )
    }

    @Provides
    @Singleton
    fun provideFirstInitializingUseCase(
        sharedPreferencesAppSettings: SharedPreferencesAppSettings,
    ): FirstInitializingUseCase {
        return FirstInitializingUseCase(
            sharedPreferencesAppSettings,
        )
    }
}
