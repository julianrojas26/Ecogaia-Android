package com.example.ecogaia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class activity_usuario : AppCompatActivity() {
    var txtNombre: EditText? = null
    var txtCorreo: EditText? = null
    var txtDireccion: EditText? = null
    var txtContra: EditText? = null
    var txtConfirmacion: EditText? = null
    var txtTelefono: EditText? = null
    var txtRol: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        txtNombre = findViewById(R.id.txtNombre)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtContra = findViewById(R.id.txtContra)
        txtTelefono = findViewById(R.id.txtTelefono)
    }

    fun clickAddUsuario(view: View) {
        val bundle = intent.extras
        val ip = bundle!!.getString("url").toString()

        val url = ip +"insertarUsuario"

        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Usuario Creado exitosamente", Toast.LENGTH_LONG).show()
                finish()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Usuario No Creado $error", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()

                params.put("usu_nombre", txtNombre?.text.toString())
                params.put("usu_contrasenia", txtContra?.text.toString())
                params.put("usu_correo", txtCorreo?.text.toString())
                params.put("usu_direccion", txtDireccion?.text.toString())
                params.put("usu_telefono", txtTelefono?.text.toString())
                return params
                Log.e("params", "$params")
            }
        }
        queue.add(resultadoPost)
    }

    fun inicio(view: View?) {
        finish()
    }

}