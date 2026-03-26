package pe.edu.idat.app_retrofit_persistence.util

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import pe.edu.idat.app_retrofit_persistence.R

object AppMensaje {
    fun enviarMensaje(vista: View,
                      mensaje:String,
                      tipoMensaje: TipoMensaje){
        val snackbar = Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG)
        if(tipoMensaje == TipoMensaje.ERROR){
            snackbar.setBackgroundTint(ContextCompat.getColor(MiApp.instance,
                R.color.error))
        }else{
            snackbar.setBackgroundTint(ContextCompat.getColor(MiApp.instance,
                R.color.success))
        }
        snackbar.show()
    }
}