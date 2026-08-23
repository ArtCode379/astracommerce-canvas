package astracommercetrade.art.astracanvas.di

import astracommercetrade.art.astracanvas.data.datastore.JVONGOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { JVONGOnboardingPrefs(androidContext()) }
}