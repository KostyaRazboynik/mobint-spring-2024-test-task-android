package com.kostyarazboynik.company_card_list.datasource.remote.repository

import com.kostyarazboynik.company_card_list.datasource.remote.CompaniesListApi
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompaniesListRemoteRepositoryImpl @Inject constructor(
    private val service: CompaniesListApi,
) : CompaniesListRemoteRepository {

    override suspend fun getRemoteCompaniesListFlow(
        limit: Int,
        skip: Int
    ): Flow<DataState<List<CompanyCard>>> = flow {
        try {
            val networkListResponse = service.getList(limit, skip)

            if (networkListResponse.isSuccessful) {
                networkListResponse.body()?.let { response ->
                    emit(DataState.Result(response.list))
                }
            } else {
                networkListResponse.errorBody()?.close()
            }
        } catch (exception: Exception) {
            emit(DataState.Exception(exception))
        }
    }

    private companion object {
        private const val TAG = "CompaniesListRemoteRepositoryImpl"
    }
}