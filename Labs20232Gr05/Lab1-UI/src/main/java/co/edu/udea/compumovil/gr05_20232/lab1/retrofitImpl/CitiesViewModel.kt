package co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CitiesViewModel: ViewModel() {
    var cities:Cities = Cities(false, ArrayList())
    fun getCities(country: Country) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getColombiaCities(country)
            withContext(Dispatchers.Main) {
                cities.data = response.body()!!.data
            }
        }
    }
}