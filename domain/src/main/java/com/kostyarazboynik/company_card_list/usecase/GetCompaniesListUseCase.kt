package com.kostyarazboynik.company_card_list.usecase

import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import com.kostyarazboynik.company_card_list.model.UiState
import com.kostyarazboynik.company_card_list.repository.CompaniesListRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCompaniesListUseCase(
    private val companiesListRemoteRepository: CompaniesListRemoteRepository
) {

    suspend fun getCompaniesListFlow(limit: Int, skip: Int): Flow<UiState<List<CompanyCard>>> = flow {
        companiesListRemoteRepository.getRemoteCompaniesListFlow(limit, skip).collect { state ->
            when (state) {
                DataState.Initial -> emit(UiState.Initial)
                is DataState.Exception -> emit(UiState.Error(state.cause.message.toString()))
                is DataState.Result -> {
                    emit(UiState.Success(state.data))
                }
            }
        }
    }
}
