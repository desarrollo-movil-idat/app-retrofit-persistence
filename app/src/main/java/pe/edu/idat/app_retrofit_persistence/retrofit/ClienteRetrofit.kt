package pe.edu.idat.app_retrofit_persistence.retrofit

import okhttp3.OkHttpClient
import pe.edu.idat.app_retrofit_persistence.retrofit.api.PatitasRest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClienteRetrofit {
    //Configurar el tiempo de integración con las API Rest
    private var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        //.addInterceptor(ApiInterceptor()) -> Solo para manejo de TOKENS
        .build()
    //Creación del objeto Retrofit
    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitPersonajeServicio : PatitasRest by lazy {
        buildRetrofit().create(PatitasRest::class.java)
    }
}