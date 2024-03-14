package com.kostyarazboynik.company_card_list.repository

import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import kotlinx.coroutines.flow.Flow

interface CompaniesListRemoteRepository {
    suspend fun getRemoteCompaniesListFlow(): Flow<DataState<List<CompanyCard>>>
}