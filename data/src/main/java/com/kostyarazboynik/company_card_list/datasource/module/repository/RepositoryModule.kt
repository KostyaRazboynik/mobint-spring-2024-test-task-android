package com.kostyarazboynik.company_card_list.datasource.module.repository

import com.kostyarazboynik.company_card_list.datasource.remote.repository.CompaniesListRemoteRepositoryImpl
import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface RepositoryModule {

    @Reusable
    @Binds
    fun bindCompaniesListRemoteRepository(
        companiesListRemoteRepository: CompaniesListRemoteRepositoryImpl,
    ): CompaniesListRemoteRepository
}
