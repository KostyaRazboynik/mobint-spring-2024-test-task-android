package com.kostyarazboynik.company_card_list.usecase

import com.kostyarazboynik.company_card_list.Logger
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import com.kostyarazboynik.company_card_list.model.UiState
import com.kostyarazboynik.company_card_list.repository.CompaniesListLocalRepository
import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class GetMergedCompaniesListUseCase(
    private val companiesListRemoteRepository: CompaniesListRemoteRepository,
    private val companiesListLocalRepository: CompaniesListLocalRepository,
) {

    suspend fun getCompaniesListFlow(): Flow<UiState<List<CompanyCard>>> = flow {
        Logger.d(TAG, "getCompaniesListFlow")

        mergeCompaniesListsDataStateFlow(
            companiesListRemoteRepository.getRemoteCompaniesListFlow(),
            companiesListLocalRepository.getLocalCompaniesList(),
        ).collect { state ->
            when (state) {
                DataState.Initial -> emit(UiState.Initial)
                is DataState.Exception -> emit(UiState.Error(state.cause.message.toString()))
                is DataState.Result -> {
                    companiesListLocalRepository.updateLocalDatabase(state.data)
                    Logger.d(TAG, "list=${state.data}")
                    emit(UiState.Success(state.data))
                }
            }
        }
    }

    private fun mergeCompaniesListsDataStateFlow(
        remoteCompaniesFlow: Flow<DataState<List<CompanyCard>>>,
        localCompanies: List<CompanyCard>,
    ): Flow<DataState<List<CompanyCard>>> = flow {
        remoteCompaniesFlow.collect { remoteCompanies ->
            Logger.d(TAG, "remoteCompanies=$remoteCompanies")
            Logger.d(TAG, "localCompanies=$localCompanies")
            when (remoteCompanies) {
                is DataState.Result -> emit(
                    mergeCompaniesLists(
                        remoteCompanies.data,
                        localCompanies
                    )
                )

                is DataState.Initial,
                is DataState.Exception -> emit(DataState.Result(localCompanies))
            }
        }
    }

    private fun mergeCompaniesLists(
        remoteCompanies: List<CompanyCard>,
        localCompanies: List<CompanyCard>,
    ): DataState.Result<List<CompanyCard>> {
        val companiesIdLocalSet = localCompanies.map { it.company.companyId }.toSet()
        val newRemoteCompanies = remoteCompanies.filter { card ->
            card.company.companyId !in companiesIdLocalSet
        }
        val resultList = localCompanies.toMutableList()
        resultList.addAll(newRemoteCompanies)
        return DataState.Result(resultList)
    }

    private companion object {
        private const val TAG = "GetMergedCompaniesListUseCase"
    }
}
