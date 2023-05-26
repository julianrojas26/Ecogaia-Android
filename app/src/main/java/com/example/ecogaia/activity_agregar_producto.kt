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

class activity_agregar_producto : AppCompatActivity() {
    var txtNombre: EditText? = null
    var txtPrecio: EditText? = null
    var txtCantidad: EditText? = null
    var txtCategoria: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)

        txtNombre = findViewById(R.id.txtNom)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtCantidad = findViewById(R.id.txtCant)
        txtCategoria = findViewById(R.id.txtCategoria)
    }

    fun clickAddProducts(view: View) {
        val url = "http://192.168.0.11:8080/insertarProducto"
        val queue = Volley.newRequestQueue(this)
        val resultPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Producto Creado exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Producto No Creado $error", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()

                params.put("prod_Precio", txtPrecio?.text.toString())
                params.put("prod_Nombre", txtNombre?.text.toString())
                params.put("prod_Cantidad", txtCantidad?.text.toString())
                params.put("prod_Categoria", txtCategoria?.text.toString())
                Log.e("params", "$params")
                return params
            }
        }
        queue.add(resultPost)
    }


}
