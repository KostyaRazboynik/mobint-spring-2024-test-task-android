package com.kostyarazboynik.company_card_list.ui.companies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kostyarazboynik.company_card_list.Logger
import com.kostyarazboynik.company_card_list.extensions.launchNamed
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.UiState
import com.kostyarazboynik.company_card_list.usecase.FirstInitializingUseCase
import com.kostyarazboynik.company_card_list.usecase.GetLocalCompaniesListUseCase
import com.kostyarazboynik.company_card_list.usecase.GetMergedCompaniesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class CompaniesListFragmentViewModel(
    private val getMergedCompaniesListUseCase: GetMergedCompaniesListUseCase,
    private val getLocalCompaniesListUseCase: GetLocalCompaniesListUseCase,
    private val firstInitializingUseCase: FirstInitializingUseCase,
) : ViewModel() {

    private val _companiesListFlow: MutableStateFlow<UiState<List<CompanyCard>>> =
        MutableStateFlow(UiState.Initial)
    val companiesListFlow: StateFlow<UiState<List<CompanyCard>>> = _companiesListFlow

    fun isFirstInitializing() = firstInitializingUseCase.isFirstInitializing()
    fun setInitialized() = firstInitializingUseCase.setInitialized()

    fun loadLocalData() {
        Logger.d(TAG, "loadData")
        viewModelScope.launchNamed("$TAG-viewModelScope-loadData", Dispatchers.IO) {
            _companiesListFlow.emitAll(
                getLocalCompaniesListUseCase.getCompaniesListFlow()
            )
        }
    }

    fun loadRemoteData() {
        Logger.d(TAG, "loadRemoteData")
        viewModelScope.launchNamed("$TAG-viewModelScope-loadRemoteData", Dispatchers.IO) {
            _companiesListFlow.emitAll(
                getMergedCompaniesListUseCase.getCompaniesListFlow()
            )
        }
    }

    class Factory @Inject constructor(
        private val getMergedCompaniesListUseCase: GetMergedCompaniesListUseCase,
        private val getLocalCompaniesListUseCase: GetLocalCompaniesListUseCase,
        private val firstInitializingUseCase: FirstInitializingUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CompaniesListFragmentViewModel(
                getMergedCompaniesListUseCase,
                getLocalCompaniesListUseCase,
                firstInitializingUseCase,
            ) as T
    }

    companion object {
        private const val TAG = "CompaniesListFragmentViewModel"
    }
}
