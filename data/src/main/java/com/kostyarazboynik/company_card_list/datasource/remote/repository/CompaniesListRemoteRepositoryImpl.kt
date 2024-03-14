package com.kostyarazboynik.company_card_list.datasource.remote.repository

import com.kostyarazboynik.company_card_list.Logger
import com.kostyarazboynik.company_card_list.datasource.remote.CompaniesListApi
import com.kostyarazboynik.company_card_list.datasource.remote.CompaniesListApi.Companion.DEFAULT_LIMIT
import com.kostyarazboynik.company_card_list.datasource.remote.CompaniesListApi.Companion.DEFAULT_OFFSET
import com.kostyarazboynik.company_card_list.datasource.remote.CompaniesListApi.Companion.TOKEN
import com.kostyarazboynik.company_card_list.datasource.remote.dto.request.GetAllCompaniesRequestParams
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class CompaniesListRemoteRepositoryImpl @Inject constructor(
    private val service: CompaniesListApi,
) : CompaniesListRemoteRepository {

    override suspend fun getRemoteCompaniesListFlow(): Flow<DataState<List<CompanyCard>>> = flow {
        try {
            val networkListResponse = service.getAllCompanies(
                TOKEN,
                GetAllCompaniesRequestParams(DEFAULT_OFFSET, DEFAULT_LIMIT)
            )
            if (networkListResponse.isSuccessful) {
                networkListResponse.body()?.let { response ->
                    Logger.d(TAG, "size=${response.companies.size}, list=${response.companies}")
                    emit(DataState.Result(response.companies))
                }
            } else {
                val exception = HttpException(networkListResponse)
                emit(DataState.Exception(exception, exception.code()))
                networkListResponse.errorBody()?.close()
            }
        } catch (exception: HttpException) {
            emit(DataState.Exception(exception, exception.code()))
        } catch (exception: Exception) {
            emit(DataState.Exception(exception, UNKNOWN_EXCEPTION_CODE))
        }
    }

    private companion object {
        private const val UNKNOWN_EXCEPTION_CODE = -1
        private const val TAG = "CompaniesListRemoteRepositoryImpl"
    }
}
