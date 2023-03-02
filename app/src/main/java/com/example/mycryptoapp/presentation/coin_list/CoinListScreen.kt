package com.example.mycryptoapp.presentation.coin_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mycryptoapp.domain.model.Coin

@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel,
){
    val state = viewModel.coinListState.value

    if (state.isLoading){
        Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    else if (state.error != "No Error Data"){
        Box(modifier = Modifier
            .fillMaxSize() , contentAlignment = Alignment.Center){
            Text(text = "Error Happened" ,)
        }
    }
    else {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(state.coins.size) {
                CoinListTile(coin = state.coins[it]) {
                    Log.d("CoinListScreen", it.toString())
                }
            }
        }
    }
}

@Composable
fun CoinListTile(
    coin : Coin,
    onItemClick : (Coin) -> Unit,
){
    Row(modifier = Modifier
        .clickable {
            onItemClick(coin)
        }
        .fillMaxWidth()
        .padding(10.dp),
    horizontalArrangement = Arrangement.SpaceBetween
        ) {
        
        Text(text = "#" + coin.rank + " " + coin.name)

        
        Text(
            text = coin.symbol ,
            color =  if (coin.name.contains('A' , true) )
                Color.Red else Color.Green,
        )



    }
}