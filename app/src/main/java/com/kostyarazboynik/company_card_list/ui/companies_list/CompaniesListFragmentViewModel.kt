package com.kostyarazboynik.company_card_list.ui.companies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kostyarazboynik.company_card_list.extensions.launchNamed
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.UiState
import com.kostyarazboynik.company_card_list.usecase.GetCompaniesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class CompaniesListFragmentViewModel(
    private val getCompaniesListUseCase: GetCompaniesListUseCase,
) : ViewModel() {

    private val _companiesListFlow: MutableStateFlow<UiState<List<CompanyCard>>> =
        MutableStateFlow(UiState.Initial)
    val companiesListFlow: StateFlow<UiState<List<CompanyCard>>> = _companiesListFlow

    fun loadData() {
        viewModelScope.launchNamed("$TAG-viewModelScope-loadData", Dispatchers.IO) {
            _companiesListFlow.emitAll(
                getCompaniesListUseCase.getCompaniesListFlow(
                    limit = RESPONSE_LIMIT,
                    skip = countResponseSkip()
                )
            )
        }
    }

    private fun countResponseSkip(): Int {
        val value = _companiesListFlow.value
        return if (value is UiState.Success) {
            value.data.size
        } else {
            0
        }
    }

    class Factory @Inject constructor(
        private val getCompaniesListUseCase: GetCompaniesListUseCase,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            CompaniesListFragmentViewModel(
                getCompaniesListUseCase,
            ) as T
    }

    companion object {
        private const val TAG = "CompaniesListFragmentViewModel"
        private const val RESPONSE_LIMIT = 20
    }
}
