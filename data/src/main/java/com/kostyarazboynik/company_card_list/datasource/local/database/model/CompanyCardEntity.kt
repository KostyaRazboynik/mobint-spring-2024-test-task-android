package com.kostyarazboynik.company_card_list.datasource.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kostyarazboynik.company_card_list.model.Company
import com.kostyarazboynik.company_card_list.model.CompanyCard
import com.kostyarazboynik.company_card_list.model.CustomerMarkParameters
import com.kostyarazboynik.company_card_list.model.LoyaltyLevel
import com.kostyarazboynik.company_card_list.model.MobileAppDashboard

@Entity(tableName = CompanyCardEntity.TABLE_NAME)
data class CompanyCardEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val companyId: String,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "requiredSum")
    val requiredSum: Int,
    @ColumnInfo(name = "markToCash")
    val markToCash: Int,
    @ColumnInfo(name = "cashToMark")
    val cashToMark: Int,
    @ColumnInfo(name = "mark")
    val mark: Int,
    @ColumnInfo(name = "companyName")
    val companyName: String,
    @ColumnInfo(name = "logo")
    val logo: String,
    @ColumnInfo(name = "backgroundColor")
    val backgroundColor: String,
    @ColumnInfo(name = "mainColor")
    val mainColor: String,
    @ColumnInfo(name = "cardBackgroundColor")
    val cardBackgroundColor: String,
    @ColumnInfo(name = "textColor")
    val textColor: String,
    @ColumnInfo(name = "highlightTextColor")
    val highlightTextColor: String,
    @ColumnInfo(name = "accentColor")
    val accentColor: String,
) {
    companion object {
        const val TABLE_NAME = "companies"
    }

    fun toModel(): CompanyCard =
        CompanyCard(
            company = Company(
                companyId = companyId
            ),
            customerMarkParameters = CustomerMarkParameters(
                loyaltyLevel = LoyaltyLevel(
                    number = number,
                    name = name,
                    requiredSum = requiredSum,
                    markToCash = markToCash,
                    cashToMark = cashToMark,
                ),
                mark = mark,
            ),
            mobileAppDashboard = MobileAppDashboard(
                companyName = companyName,
                logo = logo,
                backgroundColor = backgroundColor,
                mainColor = mainColor,
                cardBackgroundColor = cardBackgroundColor,
                textColor = textColor,
                highlightTextColor = highlightTextColor,
                accentColor = accentColor
            )
        )
}

fun CompanyCard.toEntity(): CompanyCardEntity =
    CompanyCardEntity(
        companyId = this.company.companyId,
        number = this.customerMarkParameters.loyaltyLevel.number,
        name = this.customerMarkParameters.loyaltyLevel.name,
        requiredSum = this.customerMarkParameters.loyaltyLevel.requiredSum,
        markToCash = this.customerMarkParameters.loyaltyLevel.markToCash,
        cashToMark = this.customerMarkParameters.loyaltyLevel.cashToMark,
        mark = this.customerMarkParameters.mark,
        companyName = this.mobileAppDashboard.companyName,
        logo = this.mobileAppDashboard.logo,
        backgroundColor = this.mobileAppDashboard.backgroundColor,
        mainColor = this.mobileAppDashboard.mainColor,
        cardBackgroundColor = this.mobileAppDashboard.cardBackgroundColor,
        textColor = this.mobileAppDashboard.textColor,
        highlightTextColor = this.mobileAppDashboard.highlightTextColor,
        accentColor = this.mobileAppDashboard.accentColor,
    )
