package com.kostyarazboynik.company_card_list.model

import com.google.gson.annotations.SerializedName

data class CompanyCard(
    @SerializedName("company")
    val company: Company,
    @SerializedName("customerMarkParameters")
    val customerMarkParameters: CustomerMarkParameters,
    @SerializedName("mobileAppDashboard")
    val mobileAppDashboard: MobileAppDashboard,
)

data class Company(
    @SerializedName("companyId")
    val companyId: String
)

data class CustomerMarkParameters(
    @SerializedName("loyaltyLevel")
    val loyaltyLevel: LoyaltyLevel,
    @SerializedName("mark")
    val mark: Int,
)

data class LoyaltyLevel(
    @SerializedName("number")
    val number: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("requiredSum")
    val requiredSum: Int,
    @SerializedName("markToCash")
    val markToCash: Int,
    @SerializedName("cashToMark")
    val cashToMark: Int,
)

data class MobileAppDashboard(
    @SerializedName("companyName")
    val companyName: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("backgroundColor")
    val backgroundColor: String,
    @SerializedName("mainColor")
    val mainColor: String,
    @SerializedName("cardBackgroundColor")
    val cardBackgroundColor: String,
    @SerializedName("textColor")
    val textColor: String,
    @SerializedName("highlightTextColor")
    val highlightTextColor: String,
    @SerializedName("accentColor")
    val accentColor: String,
)
