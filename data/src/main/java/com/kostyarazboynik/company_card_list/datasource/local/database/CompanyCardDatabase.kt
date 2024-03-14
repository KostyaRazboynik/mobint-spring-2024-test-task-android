package com.kostyarazboynik.company_card_list.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kostyarazboynik.company_card_list.datasource.local.database.dao.CompanyCardDao
import com.kostyarazboynik.company_card_list.datasource.local.database.model.CompanyCardEntity

@Database(entities = [CompanyCardEntity::class], version = 1)
abstract class CompanyCardDatabase : RoomDatabase() {
    abstract fun companyCardDao(): CompanyCardDao
}