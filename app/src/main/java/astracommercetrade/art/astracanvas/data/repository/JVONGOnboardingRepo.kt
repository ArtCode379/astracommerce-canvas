package astracommercetrade.art.astracanvas.data.repository

import astracommercetrade.art.astracanvas.data.datastore.JVONGOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class JVONGOnboardingRepo(
    private val jvongOnboardingStoreManager: JVONGOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return jvongOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            jvongOnboardingStoreManager.setOnboardedState(state)
        }
    }
}