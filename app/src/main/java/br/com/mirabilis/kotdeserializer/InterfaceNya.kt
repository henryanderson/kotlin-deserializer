package br.com.mirabilis.kotdeserializer

import retrofit2.Call
import retrofit2.http.GET

interface InterfaceNya {

    @get:GET("kacau.php")
    val getKacau: Call<ModelNya>

}
