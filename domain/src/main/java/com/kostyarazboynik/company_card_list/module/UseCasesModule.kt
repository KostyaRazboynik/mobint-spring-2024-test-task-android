package com.kostyarazboynik.company_card_list.module

import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import com.kostyarazboynik.company_card_list.usecase.GetCompaniesListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetCompaniesListUseCase(
        companiesListRemoteRepository: CompaniesListRemoteRepository,
    ): GetCompaniesListUseCase {
        return GetCompaniesListUseCase(
            companiesListRemoteRepository,
        )
    }
}
