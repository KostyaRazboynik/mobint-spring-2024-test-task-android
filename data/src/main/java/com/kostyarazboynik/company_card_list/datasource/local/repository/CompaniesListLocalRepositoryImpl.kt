package com.kostyarazboynik.company_card_list.datasource.local.repository

import com.kostyarazboynik.company_card_list.datasource.local.database.dao.CompanyCardDao
import com.kostyarazboynik.company_card_list.datasource.local.database.model.toEntity
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import com.kostyarazboynik.company_card_list.repository.CompaniesListLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompaniesListLocalRepositoryImpl @Inject constructor(
    private val companyCardDao: CompanyCardDao,
) : CompaniesListLocalRepository {

    override suspend fun getLocalCompaniesListFlow(): Flow<DataState<List<CompanyCard>>> = flow {
        emit(DataState.Initial)
        companyCardDao.getCompaniesFlow().collect { list ->
            emit(DataState.Result(list.map { it.toModel() }))
        }
    }

    override suspend fun getLocalCompaniesList(): List<CompanyCard> =
        companyCardDao.getCompanies().map { it.toModel() }


    override suspend fun updateLocalDatabase(mergedCompaniesCard: List<CompanyCard>) =
        companyCardDao.mergeCompanies(mergedCompaniesCard.map { it.toEntity() })

    private companion object {
        private const val TAG = "CompaniesListLocalRepositoryImpl"
    }
}
