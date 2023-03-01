package com.example.mycryptoapp.domain.use_case

import com.example.mycryptoapp.common.Resource
import com.example.mycryptoapp.domain.model.Coin
import com.example.mycryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCoinsUC(
    private val repository: CoinRepository
) {
    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoin().map { coinDTO ->
                coinDTO.toCoin()
            }
            emit(Resource.Success(coins))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}