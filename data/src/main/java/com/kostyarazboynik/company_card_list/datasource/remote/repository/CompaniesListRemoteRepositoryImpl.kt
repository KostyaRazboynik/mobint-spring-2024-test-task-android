package com.kostyarazboynik.company_card_list.datasource.remote.repository

import com.kostyarazboynik.company_card_list.Logger
import com.kostyarazboynik.company_card_list.datasource.remote.CompaniesListApi
import com.kostyarazboynik.company_card_list.datasource.remote.dto.request.GetAllCompaniesRequestParams
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompaniesListRemoteRepositoryImpl @Inject constructor(
    private val service: CompaniesListApi,
) : CompaniesListRemoteRepository {

    override suspend fun getRemoteCompaniesListFlow(): Flow<DataState<List<CompanyCard>>> = flow {
        try {
            val networkListResponse = service.getAllCompaniesIdeal(
                TOKEN,
                GetAllCompaniesRequestParams(DEFAULT_OFFSET, DEFAULT_LIMIT)
            )
            if (networkListResponse.isSuccessful) {
                networkListResponse.body()?.let { response ->
                    Logger.d(TAG, "size=${response.companies.size}, list=${response.companies}")
                    emit(DataState.Result(response.companies))
                }
            } else {
                networkListResponse.errorBody()?.close()
            }
        } catch (exception: Exception) {
            emit(DataState.Exception(exception))
        }
    }

    private companion object {
        private const val TOKEN = "123"
        private const val DEFAULT_OFFSET = 0
        private const val DEFAULT_LIMIT = 10
        private const val TAG = "CompaniesListRemoteRepositoryImpl"
    }
}
