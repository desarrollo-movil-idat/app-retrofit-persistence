package pe.edu.idat.app_retrofit_persistence.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.idat.app_retrofit_persistence.R
import pe.edu.idat.app_retrofit_persistence.databinding.ActivityMainBinding
import pe.edu.idat.app_retrofit_persistence.retrofit.ClienteRetrofit
import pe.edu.idat.app_retrofit_persistence.retrofit.request.LoginRequest
import pe.edu.idat.app_retrofit_persistence.retrofit.response.LoginResponse
import pe.edu.idat.app_retrofit_persistence.util.AppMensaje
import pe.edu.idat.app_retrofit_persistence.util.TipoMensaje
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnlogin.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        login()
    }
    private fun login(){
        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            AppMensaje.enviarMensaje(binding.root, "Ingrese su usuario", TipoMensaje.ERROR)
        }else if(binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            AppMensaje.enviarMensaje(binding.root, "Ingrese su password", TipoMensaje.ERROR)
        }else {
            val request : Call<LoginResponse> = ClienteRetrofit
                .retrofitPersonajeServicio.autenticacion(LoginRequest(
                    binding.etusuario.text.toString(),
                    binding.etpassword.text.toString()
                ))
            request.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse?>,
                    response: Response<LoginResponse?>
                ) {
                    val loginResponse = response.body()!!
                    if(loginResponse.rpta){
                        startActivity(Intent(applicationContext, HomeActivity::class.java))
                        finish()
                    }else{
                        AppMensaje.enviarMensaje(binding.root, loginResponse.mensaje,
                            TipoMensaje.ERROR)
                    }
                }
                override fun onFailure(call: Call<LoginResponse?>,t: Throwable) {
                    Log.e("ERRORAPI", t.message.toString())
                }

            })

        }
    }

}