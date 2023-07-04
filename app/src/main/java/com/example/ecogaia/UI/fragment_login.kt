package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class fragment_login : AppCompatActivity() {

    var conUsuario: EditText? = null
    var conContraseña: EditText? = null
    var buttonLogin: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        this.conUsuario = findViewById(R.id.usuario)
        this.conContraseña = findViewById(R.id.contrasenia)
        this.buttonLogin = findViewById(R.id.login)
        val registrarse = findViewById<Button>(R.id.registrarse)
        val url = "http://192.168.0.11:8080/"

        registrarse.setOnClickListener() {
            val i = Intent(this, activity_usuario::class.java).apply { }
            val bundle = Bundle()
            bundle.putString("url", url)
            i.putExtras(bundle)
            startActivity(i)
        }

        this.buttonLogin?.setOnClickListener() {
            Log.w("usario", this.conUsuario?.text.toString())
            Log.w("contrasenia", this.conContraseña?.text.toString())

            if (this.conUsuario!!.text.isEmpty() || this.conUsuario!!.text.isEmpty()) {
                Toast.makeText(this, "Debes Completar Todos Los Campos", Toast.LENGTH_LONG).show()
            } else {
                val url = url + "validarUsuario/" + this.conUsuario?.text.toString() + "/" + this.conContraseña?.text.toString()
                val queue = Volley.newRequestQueue(this)
                val resultadoPost = StringRequest(Request.Method.GET, url, { response ->
                        if (JSONObject(response).getString("error") == "null") {
                            var intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("user", response)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this,
                                JSONObject(response).getString("error"),
                                Toast.LENGTH_LONG).show()
                        }
                    }, { error ->
                        Toast.makeText(this, "Usuario No Creado $error", Toast.LENGTH_LONG).show()
                    }
                )
                queue.add(resultadoPost)

            }
        }
    }
}