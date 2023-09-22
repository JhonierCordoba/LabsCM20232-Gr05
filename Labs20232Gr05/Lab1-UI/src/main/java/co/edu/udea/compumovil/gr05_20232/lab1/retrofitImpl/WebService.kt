package co.edu.udea.compumovil.gr05_20232.lab1.retrofitImpl

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
interface WebService {
    @POST
    fun getColombiaCities(@Body country:Country): Response<Cities>
}


