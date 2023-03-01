package com.example.mycryptoapp.data.network

import com.example.mycryptoapp.data.network.dto.CoinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface PeprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>
}