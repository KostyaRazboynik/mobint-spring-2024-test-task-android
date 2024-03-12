package com.kostyarazboynik.company_card_list.datasource.remote

import com.kostyarazboynik.company_card_list.datasource.remote.dto.response.CompaniesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CompaniesListApi {

    @GET("products")
    suspend fun getList(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): Response<CompaniesListResponse>
}