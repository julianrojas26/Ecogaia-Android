package com.example.ecogaia

import VolleyMultipartRequest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.IOException

class activity_agregar_producto : AppCompatActivity() {
    private lateinit var btnUpload: Button
    var txtNombre: EditText? = null
    var txtPrecio: EditText? = null
    var txtCantidad: EditText? = null
    var txtCategoria: EditText? = null
    private var prod_Imagen: Uri? = null
    private val SELECT_IMAGE_REQUEST = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)
        btnUpload = findViewById(R.id.btnImagen)
        txtNombre = findViewById(R.id.txtNom)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtCantidad = findViewById(R.id.txtCant)
        txtCategoria = findViewById(R.id.txtCategoria)

        btnUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, SELECT_IMAGE_REQUEST)
        }
    }

    fun clickAddProducts(view: View) {
        val bundle = intent.extras
        val ip = bundle!!.getString("url").toString()

        val url = ip + "insertarProducto"
        val queue = Volley.newRequestQueue(this)
        val resultPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                if (prod_Imagen != null) {
                    try {
                        val url = ip + "guardarImagen/" + response
                        val inputStream = contentResolver.openInputStream(prod_Imagen!!)
                        val bytes = inputStream?.readBytes()
                        inputStream?.close()

                        if (bytes != null) {
                            val dataPart = VolleyMultipartRequest.Part("file", "filename.jpg", bytes, "image/jpeg")
                            val volleyMultipartRequest = object : VolleyMultipartRequest(Request.Method.POST, url,  Response.Listener { response ->
                                Toast.makeText(this, "Producto Creado exitosamente", Toast.LENGTH_LONG).show()
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
                } else {
                    Toast.makeText(this, "Producto Creado exitosamente", Toast.LENGTH_LONG).show()
                }
                finish()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            prod_Imagen = data?.data
        }
    }


}
