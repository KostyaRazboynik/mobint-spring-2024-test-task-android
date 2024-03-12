package com.kostyarazboynik.company_card_list

import com.kostyarazboynik.company_card_list.dagger.ScopeComponentProvider
import com.kostyarazboynik.company_card_list.ui.dagger.UIComponent
import kotlinx.coroutines.CoroutineScope


class MainActivityUIComponentProviderDelegate : ScopeComponentProvider<UIComponent>() {

    fun onActivityCreate(
        createComponent: (CoroutineScope) -> UIComponent
    ) = store(
        coroutineScopeName = "MainActivity.UIComponent",
        createComponent = createComponent
    )

    fun onActivityDestroy() = clear()
}
