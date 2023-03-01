package com.example.mycryptoapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycryptoapp.domain.repository.CoinRepository
import com.example.mycryptoapp.domain.use_case.GetCoinsUC

class CoinViewModelFactory(private val getCoinsUC: GetCoinsUC) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinListViewModel (getCoinsUC) as T
    }
}