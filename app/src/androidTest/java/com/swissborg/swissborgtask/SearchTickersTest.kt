package com.swissborg.swissborgtask

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.swissborg.swissborgtask.data.local.SwissBorgDatabase
import com.swissborg.swissborgtask.domain.mappers.toModel
import com.swissborg.swissborgtask.domain.models.ui.TickerModel
import com.swissborg.swissborgtask.domain.usecases.SearchTickers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SearchTickersTest {

    private lateinit var searchTickers: SearchTickers
    private lateinit var testTickersRepositoryImpl: TestTickersRepositoryImpl
    private lateinit var db: SwissBorgDatabase

    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room
            .inMemoryDatabaseBuilder(context, SwissBorgDatabase::class.java)
            .build()
        val tickersDao = db.tickersDao
        testTickersRepositoryImpl = TestTickersRepositoryImpl(tickersDao = tickersDao)
        searchTickers = SearchTickers(tickerRepository = testTickersRepositoryImpl)
    }

    @Test
    fun testEmptyQuery() = runTest(mainCoroutineRule.testDispatcher) {
        val searchQuery = ""
        testTickersRepositoryImpl.insertItems(TestTickersRepositoryImpl.fullList)

        val actual = searchTickers(searchQuery).first()
        val expected = TestTickersRepositoryImpl.fullList.map { it.toModel() }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testBitcoinQuery() = runTest(mainCoroutineRule.testDispatcher) {
        val searchQuery = "bitcoin"
        testTickersRepositoryImpl.insertItems(TestTickersRepositoryImpl.fullList)

        val actual = searchTickers(searchQuery).first()
        val expected =
            TestTickersRepositoryImpl.fullList.map { it.toModel() }.searchItems(searchQuery)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testCoinQuery() = runTest(mainCoroutineRule.testDispatcher) {
        val searchQuery = "coin"
        testTickersRepositoryImpl.insertItems(TestTickersRepositoryImpl.fullList)

        val actual = searchTickers(searchQuery).first()
        val expected =
            TestTickersRepositoryImpl.fullList.map { it.toModel() }.searchItems(searchQuery)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testAbcdeQuery() = runTest(mainCoroutineRule.testDispatcher) {
        val searchQuery = "abcde"
        testTickersRepositoryImpl.insertItems(TestTickersRepositoryImpl.fullList)

        val actual = searchTickers(searchQuery).first()
        val expected =
            TestTickersRepositoryImpl.fullList.map { it.toModel() }.searchItems(searchQuery)

        assertThat(actual).isEqualTo(expected)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    private fun List<TickerModel>.searchItems(searchQuery: String): List<TickerModel> =
        filter {
            it.friendlyName?.contains(searchQuery, ignoreCase = true) == true
                    || it.apiName?.contains(searchQuery, ignoreCase = true) == true
                    || it.tickerDetails.symbol.contains(searchQuery, ignoreCase = true)
        }
}
