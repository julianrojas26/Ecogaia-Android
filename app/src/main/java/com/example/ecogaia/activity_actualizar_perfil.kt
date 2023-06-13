package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import org.json.JSONObject
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class activity_actualizar_perfil : AppCompatActivity() {
    private lateinit var txtNombre : TextView
    private lateinit var txtCorreo : TextView
    private lateinit var txtDireccion : TextView
    private lateinit var txtContra : TextView
    private lateinit var txtTelefono : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_perfil)
        txtNombre = findViewById(R.id.txtNombre)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtContra = findViewById(R.id.txtContra)
        txtTelefono = findViewById(R.id.txtTelefono)

        val bundle = intent.extras
        val user:JSONObject = JSONObject(bundle!!.getString("user"))
        val ip = bundle!!.getString("url")

        val url = ip + "usuario/"+user.getString("res")
        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONObject(response)
            this.txtNombre.text = jsonArray.getString("usu_nombre")
            this.txtCorreo.text = jsonArray.getString("usu_correo")
            this.txtDireccion.text = jsonArray.getString("usu_direccion")
            this.txtContra.text = jsonArray.getString("usu_contrasenia")
            this.txtTelefono.text = jsonArray.getString("usu_telefono")
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)

    }


     fun clickActualizarPerfil( view: View){
         val bundle = intent.extras
         val ip = bundle!!.getString("url").toString()
         val  user = JSONObject(bundle!!.getString("user"))

         Log.w("user", user.toString())
         Log.w("url", ip)

         val  url = ip + "actualizarUsuario/" + user.getString("res")
         val queue = Volley.newRequestQueue(this)
         val resultPost = object : StringRequest(Request.Method.PUT, url,
             Response.Listener<String> { response ->
                 Toast.makeText(this, "Se Actualizo Correctamente", Toast.LENGTH_LONG).show()
             }, Response.ErrorListener { error ->
                 Toast.makeText(this, "No se pudo Actualizar $error", Toast.LENGTH_LONG).show()
             }
         ) {
             override fun getParams(): MutableMap<String, String>? {
                 val params = HashMap<String, String>()
                 params.put("usu_nombre", txtNombre?.text.toString())
                 params.put("usu_direccion", txtDireccion?.text.toString())
                 params.put("usu_correo", txtCorreo?.text.toString())
                 params.put("usu_contrasenia", txtContra?.text.toString())
                 params.put("usu_telefono", txtTelefono?.text.toString())
                 return params
             }
         }
         queue.add(resultPost)
    }

}