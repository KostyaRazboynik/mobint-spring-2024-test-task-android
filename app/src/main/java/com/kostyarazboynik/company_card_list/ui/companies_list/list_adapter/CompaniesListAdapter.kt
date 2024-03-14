package com.kostyarazboynik.company_card_list.ui.companies_list.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kostyarazboynik.company_card_list.databinding.CompaniesListItemLayoutBinding
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.ui.companies_list.list_adapter.diffutil.CompaniesListDiffUtilCallback

class CompaniesListAdapter(
    private val loadNewCompaniesCallBack: () -> Unit,
) : ListAdapter<CompanyCard, CompaniesListViewHolder>(CompaniesListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaniesListViewHolder =
        CompaniesListViewHolder(
            CompaniesListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CompaniesListViewHolder, position: Int) {
        holder.bind(getItem(position))

        if (position == itemCount - 3) {
            // TODO loadNewCompaniesCallBack() with animation
        }
    }

    private companion object {
        private const val TAG = "ProductListItemAdapter"
    }
}
