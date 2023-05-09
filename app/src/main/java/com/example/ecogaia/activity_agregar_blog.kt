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
import com.example.ecogaia.UI.fragment_blog
import java.sql.Date
import java.sql.Time
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

class activity_agregar_blog : AppCompatActivity() {
    var id : EditText? = null
    var nombre: EditText? = null
    var titulo : EditText?= null
    var cuerpo : EditText?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_blog)

        id = findViewById(R.id.id)
        nombre = findViewById(R.id.textNombre)
        titulo = findViewById(R.id.texttitulo)
        cuerpo = findViewById(R.id.textcuerpo)
    }

    fun clickAddProducts(view: View){
        val url="http://192.168.90.2:8080/insertarTip"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(this, "Tip Creado exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{ error ->
                Toast.makeText(this, "Tip No Credo $error", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()

                params.put("comp_usuario",nombre?.text.toString())
                params.put("titulo",titulo?.text.toString())
                params.put("cuerpo",cuerpo?.text.toString())
                params.put("usuario",id?.text.toString())

                return params
                Log.e("params","$params")

            }
        }
        val con = resultadoPost.bodyContentType
        Log.e("a","$con")
        queue.add(resultadoPost)

        val i = Intent(this, MainActivity::class.java).apply {  }
        startActivity(i)
    }
}