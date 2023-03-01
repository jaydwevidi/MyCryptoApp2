package com.example.mycryptoapp.domain.repository

import com.example.mycryptoapp.data.network.dto.CoinDto

interface CoinRepository {
    suspend fun getCoin() : List<CoinDto>
    // suspend fun getCoinDetails(id : Int) : CoinDetail
}