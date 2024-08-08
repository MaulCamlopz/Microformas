package com.example.microformas.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface ServiceAPI {

    @GET("Technical?Id=126&Source=Microformas")
    suspend fun getServiceData(): ServiceResponseModel

    @POST("token")
    suspend fun postDataLogin(@Body user: UserModel): LoginResponseModel

}

object ServiceAPIFactory {

    fun makeServiceAPI():ServiceAPI {

        val urlBase = "https://smctest.microformas.com.mx/SgsMicroformas.Mobile.Cat/api/Ars/"

        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        val builder = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val service = builder.create(ServiceAPI::class.java)
        return service
    }

    fun makeLoginAPI():ServiceAPI {

        val urlBase = "https://smctest.microformas.com.mx/sgsmicroformas.login/api/auth/"

        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        val builder = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val service = builder.create(ServiceAPI::class.java)
        return service
    }

}