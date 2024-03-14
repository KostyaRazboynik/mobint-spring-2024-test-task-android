package com.kostyarazboynik.company_card_list.datasource.remote

import com.kostyarazboynik.company_card_list.datasource.remote.dto.request.GetAllCompaniesRequestParams
import com.kostyarazboynik.company_card_list.datasource.remote.dto.response.CompaniesListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CompaniesListApi {

    @POST("getAllCompanies")
    suspend fun getAllCompanies(
        @Header("TOKEN") token: String,
        @Body params: GetAllCompaniesRequestParams,
    ): Response<CompaniesListResponse>

    @POST("getAllCompaniesIdeal")
    suspend fun getAllCompaniesIdeal(
        @Header("TOKEN") token: String,
        @Body params: GetAllCompaniesRequestParams,
    ): Response<CompaniesListResponse>

    @POST("getAllCompaniesLong")
    suspend fun getAllCompaniesLong(
        @Header("TOKEN") token: String,
        @Body params: GetAllCompaniesRequestParams,
    ): Response<CompaniesListResponse>

    @POST("getAllCompaniesError")
    suspend fun getAllCompaniesError(
        @Header("TOKEN") token: String,
        @Body params: GetAllCompaniesRequestParams,
    ): Response<CompaniesListResponse>
}