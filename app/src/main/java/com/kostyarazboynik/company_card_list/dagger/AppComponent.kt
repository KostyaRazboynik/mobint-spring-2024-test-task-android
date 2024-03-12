package com.kostyarazboynik.company_card_list.dagger

import android.app.Application
import android.content.Context
import com.kostyarazboynik.company_card_list.CompaniesListApp
import com.kostyarazboynik.company_card_list.MainActivity
import com.kostyarazboynik.company_card_list.module.network.NetworkModule
import com.kostyarazboynik.company_card_list.module.UseCasesModule
import com.kostyarazboynik.company_card_list.module.repository.RepositoryModule
import com.kostyarazboynik.company_card_list.ui.dagger.UIComponent
import com.kostyarazboynik.company_card_list.ui.companies_list.CompaniesListFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        NetworkModule::class,
        UseCasesModule::class,
    ]
)
interface AppComponent {

    fun findViewModelFactory(): CompaniesListFragmentViewModel.Factory

    fun inject(app: CompaniesListApp)

    fun inject(activity: MainActivity)

    fun createUIComponent(): UIComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun componentScope(componentScope: CoroutineScope): Builder

        fun build(): AppComponent
    }

    companion object : ScopeComponentProvider<AppComponent>() {

        fun init(
            application: Application,
        ) = store(
            coroutineScopeName = "AppComponent"
        ) { componentScope ->
            DaggerAppComponent
                .builder()
                .componentScope(componentScope)
                .context(application)
                .application(application)
                .build()
        }
    }
}
