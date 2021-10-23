package com.marta.picturesfromapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.marta.picturesfromapi.apiStuff.APIRequest
import com.marta.picturesfromapi.apiStuff.BASE_URL
import com.marta.picturesfromapi.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeApiRequest()
        binding.fabtnSync.setOnClickListener{
            binding.ivRandomCat.visibility = View.GONE
            makeApiRequest()

        }
    }

    private fun makeApiRequest() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.getRandomDog()
                Log.d("Main","Id: ${response.id}")

                if(response.id < 800_000){
                    withContext(Dispatchers.Main) {
                        Glide.with(applicationContext).load(response.url).into(binding.ivRandomCat)
                        binding.ivRandomCat.visibility = View.VISIBLE

                    }
                }

            }catch (e:Exception){
                Log.d("Main","Error ${e.message}")
            }
        }

    }

}