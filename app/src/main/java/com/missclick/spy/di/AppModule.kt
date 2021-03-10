package com.missclick.spy.di

import com.missclick.spy.ui.cards.CardsViewModel
import com.missclick.spy.ui.menu.MenuViewModel
import com.missclick.spy.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MenuViewModel(get())
    }
    viewModel {
        CardsViewModel(get())
    }
    viewModel {
        SplashViewModel(get(), get())
    }
}