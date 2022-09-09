package com.swissborg.swissborgtask.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swissborg.swissborgtask.common.core.Constants.REFRESH_RATE
import com.swissborg.swissborgtask.common.wrappers.NetworkConnectivityManager
import com.swissborg.swissborgtask.domain.usecases.FetchTickers
import com.swissborg.swissborgtask.domain.usecases.SearchTickers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchTickers: FetchTickers,
    private val searchTickers: SearchTickers,
    private val networkConnectivityManager: NetworkConnectivityManager
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    private var job: Job? = null

    init {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                viewModelScope.launch {
                    val result = fetchTickers()
                    _viewState.update { it.copy(fetchTickersResult = result) }
                }
            }
        }, Date.from(Instant.now()), REFRESH_RATE)

        searchTickers()

        viewModelScope.launch {
            networkConnectivityManager.observeNetworkStatus().collect { networkStatus ->
                _viewState.update { it.copy(networkStatus = networkStatus) }
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SearchQueryChange -> _viewState.update { it.copy(searchQuery = event.searchQuery) }
            is MainEvent.SearchTickersButtonClicked -> {
                searchTickers()
            }
        }
    }

    private fun searchTickers() {
        job?.cancel()
        job = viewModelScope.launch {
            searchTickers(_viewState.value.searchQuery).collect { tickers ->
                _viewState.update { it.copy(tickers = tickers) }
            }
        }
    }
}
