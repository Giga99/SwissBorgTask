package com.swissborg.swissborgtask.presentation.main

import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.domain.models.ui.TickerUIModel

sealed class MainEvent {

}

sealed class MainSideEffect {

}

data class MainViewState(
    val tickers: Result<List<TickerUIModel>> = Result.Loading()
)
