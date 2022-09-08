package com.swissborg.swissborgtask.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.swissborg.swissborgtask.R
import com.swissborg.swissborgtask.common.core.Result
import com.swissborg.swissborgtask.common.ui.toPercentage
import com.swissborg.swissborgtask.common.ui.toPrice
import com.swissborg.swissborgtask.domain.models.ui.TickerModel
import java.math.BigDecimal

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val viewState = mainViewModel.viewState.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(dimensionResource(R.dimen.size_16))
    ) {
        Text(
            text = stringResource(R.string.welcome_title),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_32)))
        Text(
            text = stringResource(R.string.search_tickers),
            style = MaterialTheme.typography.body1
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = viewState.searchQuery,
                onValueChange = { mainViewModel.onEvent(MainEvent.SearchQueryChange(it)) },
                placeholder = { Text(text = stringResource(R.string.search_hint)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.size_8)))
            IconButton(
                onClick = {
                    keyboardController?.hide()
                    mainViewModel.onEvent(MainEvent.SearchTickersButtonClicked)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.search)
                )
            }
        }
        when (viewState.fetchTickersResult) {
            is Result.Success -> TickersListSuccess(viewState.tickers)
            is Result.Error -> TickersListError(viewState.fetchTickersResult.message ?: "")
            is Result.Loading -> TickersListLoading()
        }
    }
}

@Composable
fun ColumnScope.TickersListSuccess(
    tickers: List<TickerModel>
) {
    if (tickers.isEmpty()) {
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
    ticker: TickerModel
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
                        text = ticker.friendlyName ?: ticker.tickerDetails.symbol,
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
                            text = ticker.apiName ?: ticker.tickerDetails.symbol,
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
                    if (ticker.tickerDetails.dailyChangeRelative > BigDecimal.ZERO) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.error
                Text(
                    text = ticker.tickerDetails.lastPrice.toPrice(),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = ticker.tickerDetails.dailyChangeRelative.toPercentage(),
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
