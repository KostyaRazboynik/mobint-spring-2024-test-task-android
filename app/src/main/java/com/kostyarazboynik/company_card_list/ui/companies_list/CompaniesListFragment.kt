package com.kostyarazboynik.company_card_list.ui.companies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kostyarazboynik.company_card_list.R
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
                loadNewCompaniesCallBack = { binding.recyclerView.waitLoadingDown() }
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
            binding.swipeRefreshLayout.waitLoadingUp()
            viewModel.setInitialized()
            viewModel.loadRemoteData()
        } else {
            viewModel.loadLocalData()
        }
    }

    private fun View.waitLoadingUp() {
        alpha = 0f
        setLoaderUpVisibility(true)
        animate()
            .setDuration(COMPANIES_FIRST_LOADING_DURATION_MS)
            .withEndAction {
                setLoaderUpVisibility(false)
                animate()
                    .setDuration(SHOW_COMPANIES_LIST_DURATION_MS)
                    .alpha(1f)
                    .start()
            }
            .start()

    }

    private fun View.waitLoadingDown() {
        setLoaderDownVisibility(true)
        animate()
            .setDuration(COMPANIES_FIRST_LOADING_DURATION_MS)
            .withEndAction {
                setLoaderDownVisibility(false)
                viewModel.loadRemoteData()
            }
            .start()
    }

    private fun setLoaderUpVisibility(isVisible: Boolean) {
        binding.apply {
            progressLoaderUp.isVisible = isVisible
            companiesLoadingUp.isVisible = isVisible
        }
    }

    private fun setLoaderDownVisibility(isVisible: Boolean) {
        binding.apply {
            progressLoaderDown.isVisible = isVisible
        }
    }

    private fun updateUI() =
        viewModel.viewModelScope.launchNamed("$TAG-viewModelScope-updateUI") {
            updateStateUI()
        }

    private suspend fun updateStateUI() {
        viewModel.companiesListFlow.collect { uiState ->
            when (uiState) {
                is UiState.Initial -> Unit
                is UiState.Success -> listAdapter.submitList(uiState.data)
                is UiState.Error -> {
                    val context = requireContext()
                    when (uiState.code) {
                        400 -> makeToast(uiState.cause)
                        401 -> makeToast(context.getString(R.string.error_401))
                        500 -> makeToast(context.getString(R.string.error_500))
                        else -> makeToast(context.getString(R.string.error_unknown))
                    }
                }
            }
        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "ProductListFragment"
        private const val COMPANIES_FIRST_LOADING_DURATION_MS = 3000L
        private const val SHOW_COMPANIES_LIST_DURATION_MS = 300L

        fun newInstance() = CompaniesListFragment()
    }
}
