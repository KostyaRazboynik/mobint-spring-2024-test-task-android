package com.kostyarazboynik.company_card_list.datasource.remote.dto.response

import com.google.gson.annotations.SerializedName
import com.kostyarazboynik.company_card_list.model.CompanyCard

data class CompaniesListResponse(
    @SerializedName("companies")
    val companies: List<CompanyCard>
)
