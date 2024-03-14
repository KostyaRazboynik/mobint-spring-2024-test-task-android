package com.kostyarazboynik.company_card_list.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kostyarazboynik.company_card_list.datasource.local.database.model.CompanyCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyCardDao {

    @Query("SELECT * FROM companies")
    fun getCompaniesFlow(): Flow<List<CompanyCardEntity>>

    @Query("SELECT * FROM companies")
    fun getCompanies(): List<CompanyCardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun mergeCompanies(companies: List<CompanyCardEntity>)
}