package com.example.mycryptoapp.data.network.dto

import com.example.mycryptoapp.domain.model.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
){
    fun toCoin() = Coin(
        rank = this.rank,
        id = this.id,
        name = this.name,
        symbol = this.symbol,
    )
}