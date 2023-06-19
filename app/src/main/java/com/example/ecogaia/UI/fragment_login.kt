package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class fragment_login : AppCompatActivity() {

     var confirUsuario: String? = null
     var confirContraseña: String? = null
     var conUsuario: EditText? = null
     var conContraseña: EditText? = null
     var buttonLogin: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        this.conUsuario = findViewById(R.id.usuario)
        this.conContraseña = findViewById(R.id.contrasenia)
        this.buttonLogin = findViewById(R.id.login)

        this.buttonLogin?.setOnClickListener() {
            Log.w("usario", this.conUsuario?.text.toString())
            Log.w("contrasenia", this.conContraseña?.text.toString())

            if (this.conUsuario!!.text.isEmpty() || this.conUsuario!!.text.isEmpty()){
                Toast.makeText(this, "Debes Completar Todos Los Campos", Toast.LENGTH_LONG).show()
            } else {
<<<<<<< HEAD

                val url = "http://192.168.140.131:8080/validarUsuario/"+this.conUsuario?.text+"/"+this.conContraseña?.text

=======
<<<<<<< HEAD
                val url = "http://192.168.1.78:8080/validarUsuario/"+this.conUsuario?.text+"/"+this.conContraseña?.text
=======
                val url = "http://192.168.43.209:8080/validarUsuario/"+this.conUsuario?.text+"/"+this.conContraseña?.text
>>>>>>> 6c13fc623e83864ec339f958dd0ec9f17785bb50
>>>>>>> 46327b69e9d582aee1aac73a02f51ef264e5a854
                val queue = Volley.newRequestQueue(this)
                val resultGet = StringRequest (Request.Method.GET, url, { response ->
                    if(response != "Usuario o contraseña incorrectos"){
                        var intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("user",response)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,response, Toast.LENGTH_LONG).show()
                    }
                }, { error ->
                    Log.w("String Error", error)
                } )

                queue.add(resultGet)
            }
        }
    }
}