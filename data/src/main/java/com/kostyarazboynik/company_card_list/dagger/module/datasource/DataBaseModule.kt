package com.kostyarazboynik.company_card_list.dagger.module.datasource

import android.content.Context
import androidx.room.Room
import com.kostyarazboynik.company_card_list.datasource.local.database.CompanyCardDatabase
import com.kostyarazboynik.company_card_list.datasource.local.database.dao.CompanyCardDao
import com.kostyarazboynik.company_card_list.datasource.local.database.model.CompanyCardEntity
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object DataBaseModule {

    @Reusable
    @Provides
    fun provideCompanyCardDao(database: CompanyCardDatabase): CompanyCardDao =
        database.companyCardDao()

    @Provides
    fun provideCompanyCardDatabase(context: Context): CompanyCardDatabase =
        Room.databaseBuilder(context, CompanyCardDatabase::class.java, CompanyCardEntity.TABLE_NAME)
            .build()

}
