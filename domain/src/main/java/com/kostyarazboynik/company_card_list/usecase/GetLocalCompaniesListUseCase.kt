package com.kostyarazboynik.company_card_list.usecase

import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.DataState
import com.kostyarazboynik.company_card_list.model.UiState
import com.kostyarazboynik.company_card_list.repository.CompaniesListLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocalCompaniesListUseCase(
    private val companiesListLocalRepository: CompaniesListLocalRepository,
) {

    suspend fun getCompaniesListFlow(): Flow<UiState<List<CompanyCard>>> = flow {
        companiesListLocalRepository.getLocalCompaniesListFlow().collect { state ->
            when (state) {
                DataState.Initial -> emit(UiState.Initial)
                is DataState.Exception -> emit(
                    UiState.Error(
                        state.cause.message.toString(),
                        state.code
                    )
                )

                is DataState.Result -> {
                    emit(UiState.Success(state.data))
                }
            }
        }
    }
}