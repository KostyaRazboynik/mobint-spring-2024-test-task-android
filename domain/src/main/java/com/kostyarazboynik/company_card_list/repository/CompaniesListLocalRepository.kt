package com.kostyarazboynik.company_card_list.repository

import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import kotlinx.coroutines.flow.Flow

interface CompaniesListLocalRepository {

    suspend fun getLocalCompaniesListFlow(): Flow<DataState<List<CompanyCard>>>

    suspend fun getLocalCompaniesList(): List<CompanyCard>

    suspend fun updateLocalDatabase(mergedCompaniesCard: List<CompanyCard>)
}