package com.example.ecogaia

import android.content.Intent
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

class activity_agregar_blog : AppCompatActivity() {
    var id: EditText? = null
    var nombre: EditText? = null
    var titulo: EditText? = null
    var cuerpo: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_blog)

        id = findViewById(R.id.id)
        nombre = findViewById(R.id.textNombre)
        titulo = findViewById(R.id.texttitulo)
        cuerpo = findViewById(R.id.textcuerpo)
    }

    fun clickAddProducts(view: View) {
        val url = "http://192.168.51.2:8080/insertarTip"
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
                params.put("usuario", id?.text.toString())
                Log.e("params", "$params")
                return params

            }
        }
        queue.add(resultPost)

        val i = Intent(this, MainActivity::class.java).apply { }
        startActivity(i)
    }
}