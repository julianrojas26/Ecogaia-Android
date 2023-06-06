package com.example.ecogaia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlin.collections.HashMap

class Activity_agregarProducto : AppCompatActivity() {
    var txtNombre : EditText?= null
    var txtPrecio : EditText?= null
    var txtCantidad : EditText? = null
    var txtCategoria : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)

        txtNombre = findViewById(R.id.txtNom)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtCantidad = findViewById(R.id.txtCant)
        txtCategoria = findViewById(R.id.txtCategoria)
    }

    fun clickAddProducts(view:View){
        val url="http://192.168.136.131:8080/insertarProducto"
        val queue =Volley.newRequestQueue(this)
        val resultadoPost = object :StringRequest(Request.Method.POST, url,
            Response.Listener<String> {response->
                Toast.makeText(this, "Producto Creado exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{error ->
                Toast.makeText(this, "Producto No Credo $error", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()

                params.put("prod_Precio",txtPrecio?.text.toString())
                params.put("prod_Nombre",txtNombre?.text.toString())
                params.put("prod_Cantidad",txtCantidad?.text.toString())
                params.put("prod_Categoria",txtCategoria?.text.toString())
                return params
                Log.e("params","$params")

            }
        }
        val con = resultadoPost.bodyContentType
        Log.e("a","$con")
        queue.add(resultadoPost)
    }


}
