package com.kostyarazboynik.company_card_list.ui.companies_list.list_adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kostyarazboynik.company_card_list.databinding.CompaniesListItemLayoutBinding
import com.kostyarazboynik.company_card_list.model.CompanyCard

class CompaniesListViewHolder(
    private val binding: CompaniesListItemLayoutBinding,
    private val context: Context,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(companyCard: CompanyCard) {

        setUpCardInfo(companyCard)
        setUpButtonsListeners(companyCard)
        setUpStyle(companyCard)
    }

    private fun setUpStyle(companyCard: CompanyCard) {
        binding.apply {
            this.companyCard.background.setColorFilter(
                Color.parseColor(companyCard.mobileAppDashboard.cardBackgroundColor),
                PorterDuff.Mode.SRC
            )
        }
        setHighlightTextColor(companyCard)
        setButtonsColor(companyCard)
        setTextColor(companyCard)
    }

    private fun setButtonsColor(companyCard: CompanyCard) {
        binding.apply {
            detailsButton.apply {
                setTextColor(Color.parseColor(companyCard.mobileAppDashboard.mainColor))
                setBackgroundTintList(
                    ColorStateList.valueOf(Color.parseColor(companyCard.mobileAppDashboard.backgroundColor))
                )
            }
            deleteCardButton.setColorFilter(Color.parseColor(companyCard.mobileAppDashboard.accentColor))
            cardVisibilityButton.setColorFilter(Color.parseColor(companyCard.mobileAppDashboard.mainColor))
        }
    }

    private fun setTextColor(companyCard: CompanyCard) {
        binding.apply {
            val color = Color.parseColor(companyCard.mobileAppDashboard.textColor)
            bonusesTitle.setTextColor(color)
            cashbackTitle.setTextColor(color)
            cardLevelTitle.setTextColor(color)
        }
    }

    private fun setHighlightTextColor(companyCard: CompanyCard) {
        binding.apply {
            val color = Color.parseColor(companyCard.mobileAppDashboard.highlightTextColor)
            companyName.setTextColor(color)
            bonusesAmount.setTextColor(color)
            cashbackAmount.setTextColor(color)
            cardLevel.setTextColor(color)
        }
    }

    private fun setUpCardInfo(companyCard: CompanyCard) {
        binding.apply {
            companyName.text = companyCard.mobileAppDashboard.companyName
            companyIcon.load(companyCard.mobileAppDashboard.logo) // TODO make round
            bonusesAmount.text = companyCard.customerMarkParameters.mark.toString()
            cashbackAmount.text =
                "${companyCard.customerMarkParameters.loyaltyLevel.number}%" // TODO
            cardLevel.text = companyCard.customerMarkParameters.loyaltyLevel.name
        }
    }

    private fun setUpButtonsListeners(companyCard: CompanyCard) {
        binding.apply {
            cardVisibilityButton.setOnClickListener {
                makeToast("visibility button clicked\ncompanyId=${companyCard.company.companyId}")
            }
            deleteCardButton.setOnClickListener {
                makeToast("delete button clicked\ncompanyId=${companyCard.company.companyId}")
            }
            detailsButton.setOnClickListener {
                makeToast("details button clicked\ncompanyId=${companyCard.company.companyId}")
            }
        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
