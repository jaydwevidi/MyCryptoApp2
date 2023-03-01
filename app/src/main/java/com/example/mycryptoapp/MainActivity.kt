package com.example.mycryptoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mycryptoapp.common.Constants
import com.example.mycryptoapp.common.Resource
import com.example.mycryptoapp.data.network.PeprikaApi
import com.example.mycryptoapp.data.network.repository.CoinRepositoryImp
import com.example.mycryptoapp.domain.use_case.GetCoinsUC
import com.example.mycryptoapp.presentation.coin_list.CoinListScreen
import com.example.mycryptoapp.presentation.coin_list.CoinListViewModel
import com.example.mycryptoapp.presentation.coin_list.CoinViewModelFactory
import com.example.mycryptoapp.ui.theme.MyCryptoAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "JayDwivedi"
        super.onCreate(savedInstanceState)







        setContent {
            MyCryptoAppTheme {


                val peprikaApi = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PeprikaApi::class.java)


                val repository = CoinRepositoryImp(peprikaApi)
                val getCoinUsecase = GetCoinsUC(repository)
                val mainViewModel = ViewModelProvider(this , CoinViewModelFactory(getCoinUsecase)).get(CoinListViewModel::class.java)



                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoinListScreen(viewModel = mainViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCryptoAppTheme {
        Greeting("Android")
    }
}