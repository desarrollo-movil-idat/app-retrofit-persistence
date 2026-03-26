package pe.edu.idat.app_retrofit_persistence.retrofit.api

import pe.edu.idat.app_retrofit_persistence.retrofit.request.LoginRequest
import pe.edu.idat.app_retrofit_persistence.retrofit.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PatitasRest {

    @POST("login.php")
    fun autenticacion(@Body loginRequest: LoginRequest):
            Call<LoginResponse>

    @GET("mascotas.php")
    fun getMascotas(): List<LoginResponse>

    //localhost:8080/mascotas.php?busqueda=pepita&raza=
    @GET("mascotas.php")
    fun getBuscarMascotas(
        @Query("busqueda") busqueda:String,
        @Query("raza") raza:String)
    : List<LoginResponse>
}