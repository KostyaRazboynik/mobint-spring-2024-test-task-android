package com.kostyarazboynik.company_card_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kostyarazboynik.company_card_list.dagger.AppComponent
import com.kostyarazboynik.company_card_list.dagger.ComponentProvider
import com.kostyarazboynik.company_card_list.databinding.ActivityMainBinding
import com.kostyarazboynik.company_card_list.ui.dagger.UIComponent
import com.kostyarazboynik.company_card_list.ui.companies_list.CompaniesListFragment

class MainActivity private constructor(
    private val mainActivityUIComponentProviderDelegate: MainActivityUIComponentProviderDelegate,
) : AppCompatActivity(),
    ComponentProvider<UIComponent> by mainActivityUIComponentProviderDelegate {
    constructor() : this(
        mainActivityUIComponentProviderDelegate = MainActivityUIComponentProviderDelegate()
    )

    init {
        AppComponent.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityUIComponentProviderDelegate
            .onActivityCreate { uiScopeCoroutineScope ->
                AppComponent
                    .component
                    .createUIComponent()
                    .activity(this)
                    .appCompatActivity(this)
                    .mainActivity(this)
                    .componentScope(uiScopeCoroutineScope)
                    .build()
            }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val fragment = CompaniesListFragment.newInstance()

        createFragment(binding, fragment)
    }

    private fun createFragment(binding: ActivityMainBinding, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(binding.frameContent.id, fragment)
        transaction.commit()
    }

    override fun onDestroy() {
        mainActivityUIComponentProviderDelegate.onActivityDestroy()
        super.onDestroy()
    }
}