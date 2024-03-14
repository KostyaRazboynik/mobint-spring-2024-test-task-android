package com.kostyarazboynik.company_card_list.ui.companies_list.list_adapter

import androidx.recyclerview.widget.RecyclerView
import com.kostyarazboynik.company_card_list.databinding.CompaniesListItemLayoutBinding
import com.kostyarazboynik.company_card_list.model.CompanyCard

class CompaniesListViewHolder(
    private val binding: CompaniesListItemLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(companyCard: CompanyCard) {
        binding.apply {
            companyName.text = companyCard.mobileAppDashboard.companyName
            // TODO parse company.icon
            bonusesAmount.text = companyCard.customerMarkParameters.mark.toString()
            cashbackAmount.text = "${companyCard.customerMarkParameters.loyaltyLevel.number}%"
            cardLevel.text = companyCard.customerMarkParameters.loyaltyLevel.name
            // TODO parse colors

        }
    }
}
