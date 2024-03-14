package com.kostyarazboynik.company_card_list.datasource.remote.dto.request

import com.google.gson.annotations.SerializedName

data class GetAllCompaniesRequestParams(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
)
