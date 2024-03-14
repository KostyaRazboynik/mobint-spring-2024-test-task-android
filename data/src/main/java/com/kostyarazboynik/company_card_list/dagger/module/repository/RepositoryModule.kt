package com.kostyarazboynik.company_card_list.dagger.module.repository

import com.kostyarazboynik.company_card_list.datasource.local.repository.CompaniesListLocalRepositoryImpl
import com.kostyarazboynik.company_card_list.datasource.remote.repository.CompaniesListRemoteRepositoryImpl
import com.kostyarazboynik.company_card_list.repository.CompaniesListLocalRepository
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

    @Reusable
    @Binds
    fun bindCCompaniesListLocalRepository(
        companiesListRemoteRepository: CompaniesListLocalRepositoryImpl,
    ): CompaniesListLocalRepository
}
