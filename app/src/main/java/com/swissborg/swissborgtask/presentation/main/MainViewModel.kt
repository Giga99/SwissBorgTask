package com.swissborg.swissborgtask.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swissborg.swissborgtask.common.core.Constants.REFRESH_RATE
import com.swissborg.swissborgtask.domain.usecases.GetTickersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTickersUseCase: GetTickersUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState = _viewState.asStateFlow()

    init {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                refreshTickers()
            }
        }, Date.from(Instant.now()), REFRESH_RATE)
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SearchQueryChange -> _viewState.update { it.copy(searchQuery = event.searchQuery) }
            is MainEvent.SearchTickersButtonClicked -> {
                refreshTickers()
            }
        }
    }

    private fun refreshTickers() = viewModelScope.launch {
        val tickers = getTickersUseCase(_viewState.value.searchQuery)
        _viewState.update { it.copy(tickers = tickers) }
    }
}
