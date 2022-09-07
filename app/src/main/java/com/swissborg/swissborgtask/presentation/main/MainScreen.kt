package com.swissborg.swissborgtask.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.swissborg.swissborgtask.R
import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.common.ui.toPercentage
import com.swissborg.swissborgtask.common.ui.toPrice
import com.swissborg.swissborgtask.domain.models.ui.TickerUIModel
import java.math.BigDecimal

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val viewState = mainViewModel.viewState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(dimensionResource(R.dimen.size_16))
    ) {
        when (viewState.tickers) {
            is Result.Success -> TickersListSuccess(viewState.tickers.data)
            is Result.Error -> TickersListError(viewState.tickers.message ?: "")
            is Result.Loading -> TickersListLoading()
        }
    }
}

@Composable
fun ColumnScope.TickersListSuccess(
    tickers: List<TickerUIModel>?
) {
    if (tickers == null || tickers.isEmpty()) {
        Text(
            text = stringResource(R.string.no_tickers),
            style = MaterialTheme.typography.h6
        )
    } else {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(tickers) {
                TickerRow(ticker = it)
            }
        }
    }
}

@Composable
fun TickerRow(
    ticker: TickerUIModel
) {
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_8)))
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.size_8)),
        elevation = dimensionResource(R.dimen.size_8)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.size_16)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "ICON")
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.size_4)))
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = ticker.friendlyName ?: ticker.tickerModel.symbol,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_4)))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            shape = RoundedCornerShape(dimensionResource(R.dimen.size_4))
                        ) {
                            Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
                                Text(
                                    text = stringResource(R.string.earn_yield),
                                    style = MaterialTheme.typography.caption,
                                    color = MaterialTheme.colors.background,
                                    modifier = Modifier.padding(dimensionResource(R.dimen.size_2))
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.size_4)))
                        Text(
                            text = ticker.apiName ?: ticker.tickerModel.symbol,
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End
            ) {
                val percentageTextColor =
                    if (ticker.tickerModel.dailyChangeRelative > BigDecimal.ZERO) MaterialTheme.colors.onSurface else MaterialTheme.colors.error
                Text(
                    text = ticker.tickerModel.lastPrice.toPrice(),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = ticker.tickerModel.dailyChangeRelative.toPercentage(),
                    style = MaterialTheme.typography.caption,
                    color = percentageTextColor
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_8)))
}

@Composable
fun TickersListError(
    errorMessage: String
) {
    Text(
        text = errorMessage,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.error
    )
}

@Composable
fun ColumnScope.TickersListLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.size_16))
            .align(Alignment.CenterHorizontally)
    )
}
