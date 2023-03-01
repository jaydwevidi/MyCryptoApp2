package com.example.mycryptoapp.data.network.repository

import com.example.mycryptoapp.data.network.PeprikaApi
import com.example.mycryptoapp.data.network.dto.CoinDto
import com.example.mycryptoapp.domain.repository.CoinRepository

class CoinRepositoryImp(
    private val api: PeprikaApi
) : CoinRepository {
    override suspend fun getCoin(): List<CoinDto> {
        return api.getCoins()

    }
}