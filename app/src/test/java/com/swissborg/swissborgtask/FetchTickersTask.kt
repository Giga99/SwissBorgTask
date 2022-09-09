package com.swissborg.swissborgtask

import com.swissborg.swissborgtask.domain.repositories.TickersRepository
import com.swissborg.swissborgtask.domain.usecases.FetchTickers
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before

class FetchTickersTask {

    private lateinit var fetchTickers: FetchTickers

    @MockK
    lateinit var tickersRepository: TickersRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
//        fetchTickers = FetchTickers(tickerRepository = tickersRepository)
    }

//    @Test
//    fun `Test tickers fetching successful result`() = runTest(mainCoroutineRule.testDispatcher) {
//        val result = fetchTickers()
//    }
}
