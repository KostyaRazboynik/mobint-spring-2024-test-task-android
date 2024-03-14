package com.kostyarazboynik.company_card_list.ui.companies_list.list_adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.kostyarazboynik.company_card_list.model.CompanyCard

class CompaniesListDiffUtilCallback : DiffUtil.ItemCallback<CompanyCard>() {

    override fun areItemsTheSame(oldItem: CompanyCard, newItem: CompanyCard): Boolean =
        oldItem.company.companyId == newItem.company.companyId

    override fun areContentsTheSame(oldItem: CompanyCard, newItem: CompanyCard): Boolean =
        oldItem == newItem
}
