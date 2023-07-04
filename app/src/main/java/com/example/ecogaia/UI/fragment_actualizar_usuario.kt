package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import org.json.JSONObject


class fragment_actualizar_usuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_actualizar_usuario, container, false)

        val txtNombre = ll.findViewById<TextView>(R.id.actNombre)
        val txtCorreo = ll.findViewById<TextView>(R.id.actCorreo)
        val txtDireccion = ll.findViewById<TextView>(R.id.actDireccion)
        val txtContra = ll.findViewById<TextView>(R.id.actContra)
        val txtTelefono = ll.findViewById<TextView>(R.id.actTelefono)

        val bundle = activity?.intent?.extras
        val user = arguments?.getString("email")
        val ip = bundle!!.getString("url")

        val url = ip + "usuario/"+user
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONObject(response)
            txtNombre.text = jsonArray.getString("usu_nombre")
            txtCorreo.text = jsonArray.getString("usu_correo")
            txtDireccion.text = jsonArray.getString("usu_direccion")
            txtContra.text = jsonArray.getString("usu_contrasenia")
            txtTelefono.text = jsonArray.getString("usu_telefono")
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)

        val actualizar = ll.findViewById<Button>(R.id.actualizarUser)

        actualizar.setOnClickListener() {
            val  url = ip + "actualizarUsuario/" + user
            val queue = Volley.newRequestQueue(this.context)
            val resultPost = object : StringRequest(Request.Method.PUT, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this.context, "Se Actualizo Correctamente", Toast.LENGTH_LONG).show()
                    onDestroy()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, "No se pudo Actualizar $error", Toast.LENGTH_LONG).show()
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

        return ll
    }
}