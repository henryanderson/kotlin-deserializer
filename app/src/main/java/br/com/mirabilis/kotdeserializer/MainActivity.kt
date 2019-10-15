package br.com.mirabilis.kotdeserializer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchJSON()
    }


    private fun fetchJSON() {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(ModelNya::class.java, DeserializerNya())
        val gson = gsonBuilder.create()

        val retrofitX = Retrofit.Builder()
            .baseUrl("http://192.168.43.121/laravelandroid/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofitX.create(InterfaceNya::class.java)
        val call = api.getKacau

        call.enqueue(object : Callback<ModelNya> {
            override fun onResponse(call: Call<ModelNya>, response: Response<ModelNya>) {

                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Log.i("hasil url ", response.body()!!.url)
                        Log.i("hasil method ", response.body()!!.httpMethod)

                        if (response.body()!!.parameters != null) {
                            Log.i("hasil parameter ", response.body()!!.parameters.toString())

//                            var randomParam2 = response.body()!!.parameters?.get("randomParam2")
//                            Log.i("hasil randomParam2 ", randomParam2);
                        }

                    } else {
                        Log.i(
                            "hasil null ",
                            "Returned empty response"
                        )//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            override fun onFailure(call: Call<ModelNya>, t: Throwable) {
                Log.i("hasil fail ", t.toString())
            }
        })


    }


}
