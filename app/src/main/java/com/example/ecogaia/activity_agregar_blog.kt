package com.example.ecogaia

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class activity_agregar_blog : AppCompatActivity() {
    var nombre: EditText? = null
    var titulo: EditText? = null
    var cuerpo: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_blog)
        nombre = findViewById(R.id.textNombre)
        titulo = findViewById(R.id.texttitulo)
        cuerpo = findViewById(R.id.textcuerpo)
    }

<<<<<<< HEAD
=======


>>>>>>> 4d16e2deb4e8b1fd8d9c4b182a5d95e4f2f4c935
    fun clickAddProducts(view: View) {
        val bundle = intent.extras
        val ip = bundle!!.getString("url").toString()
        val user = JSONObject(bundle!!.getString("user"))

        Log.w("user", user.toString())
        Log.w("url", ip)

        val url = ip +"insertarTip/" + user.getString("res")

        val queue = Volley.newRequestQueue(this)
        val resultPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Tip Creado exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Tip No Creado $error", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params.put("comp_usuario", nombre?.text.toString())
                params.put("titulo", titulo?.text.toString())
                params.put("cuerpo", cuerpo?.text.toString())
                params.put("usuario", "0")
                Log.e("params", "$params")
                return params
            }
        }
        queue.add(resultPost)
    }
}