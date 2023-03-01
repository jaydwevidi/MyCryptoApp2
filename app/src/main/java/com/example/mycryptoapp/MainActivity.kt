package com.example.mycryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.mycryptoapp.common.Constants
import com.example.mycryptoapp.data.network.PeprikaApi
import com.example.mycryptoapp.data.network.repository.CoinRepositoryImp
import com.example.mycryptoapp.domain.use_case.GetCoinsUC
import com.example.mycryptoapp.presentation.coin_list.CoinListScreen
import com.example.mycryptoapp.presentation.coin_list.CoinListViewModel
import com.example.mycryptoapp.presentation.coin_list.CoinViewModelFactory
import com.example.mycryptoapp.ui.theme.MyCryptoAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCryptoAppTheme {
                val paprikaApi = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PeprikaApi::class.java)


                val repository = CoinRepositoryImp(paprikaApi)
                val getCoinsUC = GetCoinsUC(repository)
                val coinListVM = ViewModelProvider(this , CoinViewModelFactory(getCoinsUC)).get(CoinListViewModel::class.java)

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoinListScreen(viewModel = coinListVM)
                }
            }
        }
    }
}