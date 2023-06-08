package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R

import org.json.JSONObject

class fragment_perfil : Fragment() {
    private lateinit var nombre:TextView
    private lateinit var correo:TextView
    private lateinit var direccion:TextView
    private lateinit var contrasenia:TextView
    private lateinit var telefono:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val bundle = activity?.intent?.extras
        val user:JSONObject = JSONObject(bundle!!.getString("user"))
        val ip = bundle!!.getString("url")

        val ll = inflater.inflate(R.layout.fragment_perfil, container, false)

        this.nombre = ll.findViewById(R.id.usu_nombre)
        this.correo = ll.findViewById(R.id.usu_correo)
        this.direccion = ll.findViewById(R.id.usu_direccion)
        this.contrasenia = ll.findViewById(R.id.usu_contrasenia)
        this.telefono = ll.findViewById(R.id.usu_telefono)

        val url = ip + "usuario/"+user.getString("res")

        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONObject(response)
            this.nombre.text = jsonArray.getString("usu_nombre")
            this.correo.text = jsonArray.getString("usu_correo")
            this.direccion.text = jsonArray.getString("usu_direccion")
            this.contrasenia.text = jsonArray.getString("usu_contrasenia")
            this.telefono.text = jsonArray.getString("usu_telefono")
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        return ll
    }



}