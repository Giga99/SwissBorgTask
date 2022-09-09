package com.swissborg.swissborgtask

import com.google.common.truth.Truth.assertThat
import com.swissborg.swissborgtask.domain.usecases.FetchTickers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FetchTickersTask {

    private lateinit var fetchTickers: FetchTickers

    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        fetchTickers = FetchTickers(
            tickerRepository = TestTickersRepositoryImpl(),
            dispatcherProvider = mainCoroutineRule.testDispatcherProvider
        )
    }

    @Test
    fun `Test tickers fetching successful result`() = runTest(mainCoroutineRule.testDispatcher) {
        val result = fetchTickers()
        assertThat(result.data).isEqualTo(Unit)
    }
}
