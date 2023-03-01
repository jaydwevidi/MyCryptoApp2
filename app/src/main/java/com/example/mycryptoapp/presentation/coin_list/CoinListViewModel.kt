package com.example.mycryptoapp.presentation.coin_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycryptoapp.common.Resource
import com.example.mycryptoapp.domain.model.Coin
import com.example.mycryptoapp.domain.use_case.GetCoinsUC
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinListViewModel(
    val getCoinsUC : GetCoinsUC,
    ) : ViewModel(){
    private val _coinListState = mutableStateOf(CoinListState())
    val coinListState = _coinListState
    val TAG = "CoinListViewModel"

    init {
        getCoins()
    }
    private fun getCoins(){
        getCoinsUC().onEach { result ->
            Log.d(TAG, "getCoins: ${result.data} ${result.message}")
            when(result) {
                is Resource.Success -> {
                    _coinListState.value = CoinListState(coins = result.data ?: emptyList<Coin>())
                }
                is Resource.Loading -> {
                    _coinListState.value = CoinListState(isLoading = true)
                }
                is Resource.Error -> {
                    _coinListState.value = CoinListState(error = result.message ?: "Unexpected Error #343542")
                }
            }
        }.launchIn(viewModelScope)
    }
}