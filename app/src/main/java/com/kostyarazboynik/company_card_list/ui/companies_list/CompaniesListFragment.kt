package com.kostyarazboynik.company_card_list.ui.companies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kostyarazboynik.company_card_list.dagger.AppComponent
import com.kostyarazboynik.company_card_list.databinding.FragmentCompaniesListLayoutBinding
import com.kostyarazboynik.company_card_list.extensions.launchNamed
import com.kostyarazboynik.company_card_list.model.UiState
import com.kostyarazboynik.company_card_list.ui.companies_list.list_adapter.CompaniesListAdapter

class CompaniesListFragment : Fragment() {

    private val binding: FragmentCompaniesListLayoutBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private val viewModel by viewModels<CompaniesListFragmentViewModel> {
        AppComponent.component.findViewModelFactory()
    }

    private val listAdapter: CompaniesListAdapter
        get() = binding.recyclerView.adapter as CompaniesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpSwipeRefreshListener()
        setUpData()
        viewModel.loadLocalData()
        updateUI()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            adapter = CompaniesListAdapter(
                loadNewCompaniesCallBack = { viewModel.loadRemoteData() }
            )
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpSwipeRefreshListener() {
        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.loadRemoteData()
                isRefreshing = false
            }
        }
    }

    private fun setUpData() {
        if (viewModel.isFirstInitializing()) {
            // TODO show UI
            viewModel.setInitialized()
            viewModel.loadRemoteData()
        } else {
            viewModel.loadLocalData()
        }
    }

    private fun updateUI() =
        viewModel.viewModelScope.launchNamed("$TAG-viewModelScope-updateUI") {
            updateStateUI()
        }

    private suspend fun updateStateUI() {
        viewModel.companiesListFlow.collect { uiState ->
            when (uiState) {
                is UiState.Success -> listAdapter.submitList(uiState.data)
                is UiState.Initial,
                is UiState.Error -> Unit
            }
        }
    }

    companion object {
        private const val TAG = "ProductListFragment"
        fun newInstance() = CompaniesListFragment()
    }
}
