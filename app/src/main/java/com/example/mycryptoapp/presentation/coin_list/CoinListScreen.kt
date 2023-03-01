package com.example.mycryptoapp.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycryptoapp.domain.model.Coin

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel,
){
    val state = viewModel.coinListState.value

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(state.coins.size){
            CoinListTile(coin = state.coins[it])
        }
    }
}

@Composable
fun CoinListTile(
    coin : Coin,
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)) {
        Text(text = coin.name)

    }
}