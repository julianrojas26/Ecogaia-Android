package com.example.ecogaia

import VolleyMultipartRequest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.IOException

class fragment_actualizar_producto : AppCompatActivity() {
    private lateinit var btnUpload: Button
    private lateinit var btnAct: Button
    private lateinit var prod_nombre: TextView
    private lateinit var prod_precio: TextView
    private var prod_Imagen: Uri? = null
    private lateinit var prod_cantidad: TextView
    private lateinit var prod_categoria: TextView

    private val SELECT_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_actualizar_producto)

        btnUpload = findViewById(R.id.btnUpload)
        btnAct = findViewById(R.id.actProd)
        prod_nombre = findViewById(R.id.prod_nombre)
        prod_precio = findViewById(R.id.prod_precio)
        prod_cantidad = findViewById(R.id.prod_cantidad)
        prod_categoria = findViewById(R.id.prod_categoria)


        val bundle = intent.extras
        val ip = bundle!!.getString("url").toString()

        val id = bundle.getString("id")
        val url = ip +"productoCodigo/"+id
        val queue = Volley.newRequestQueue(this)

        val UPDATErequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONObject(response)
            this.prod_nombre.text = jsonArray.getString("prod_Nombre")
            this.prod_precio.text = jsonArray.getString("prod_Precio")
            this.prod_cantidad.text = jsonArray.getString("prod_Cantidad")
            this.prod_categoria.text = jsonArray.getString("prod_Categoria")
        }, { error ->
            Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
        })

        queue.add(UPDATErequest)

        btnUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, SELECT_IMAGE_REQUEST)
        }

        btnAct.setOnClickListener {
            val  url = ip + "actualizarProducto"

            val resultPost = object : StringRequest(Request.Method.PUT, url, Response.Listener<String> { response ->
                    Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                    if (prod_Imagen != null) {
                        try {
                            val url = ip + "guardarImagen/" + id
                            val inputStream = contentResolver.openInputStream(prod_Imagen!!)
                            val bytes = inputStream?.readBytes()
                            inputStream?.close()

                            if (bytes != null) {
                                val dataPart = VolleyMultipartRequest.Part("file", "filename.jpg", bytes, "image/jpeg")
                                val volleyMultipartRequest = object : VolleyMultipartRequest(Request.Method.POST, url,  Response.Listener { response ->
                                    }, Response.ErrorListener { error ->
                                    }
                                ) {
                                    init {
                                        addPart(dataPart)
                                    }
                                }
                                queue.add(volleyMultipartRequest)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                    finish()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this, "No se pudo Actualizar $error", Toast.LENGTH_LONG).show()
                }
            ) {
                override fun getParams(): MutableMap<String, String>? {
                    val params = HashMap<String, String>()
                    params.put("prod_Codigo", id!!)
                    params.put("prod_Nombre", prod_nombre?.text.toString())
                    params.put("prod_Precio", prod_precio?.text.toString())
                    params.put("prod_Cantidad", prod_cantidad?.text.toString())
                    params.put("prod_Categoria", prod_categoria?.text.toString())
                    return params
                }
            }
            queue.add(resultPost)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            prod_Imagen = data?.data
        }
    }
}