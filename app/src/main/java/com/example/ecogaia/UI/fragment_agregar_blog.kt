package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import com.example.ecogaia.adapter.DialogListener
import org.json.JSONObject

class fragment_agregar_blog : Fragment() {
    var nombre: EditText? = null
    var titulo: EditText? = null
    var cuerpo: EditText? = null
    var listener: DialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_agregar_blog, container, false)

        val bundle = activity!!.intent!!.extras
        val ip = bundle!!.getString("url").toString()
        val user = JSONObject(bundle!!.getString("user"))

        val url = ip +"insertarTip/" + user.getString("res")
        val queue = Volley.newRequestQueue(this.context)

        val button = ll.findViewById<Button>(R.id.sendBlog)
        titulo = ll.findViewById(R.id.texttitulo)
        cuerpo = ll.findViewById(R.id.textcuerpo)

        button.setOnClickListener() {
            val resultPost = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this.context, "Tip Creado exitosamente", Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, "Tip No Creado $error", Toast.LENGTH_LONG).show()
                }
            ) {
                override fun getParams(): MutableMap<String, String>? {
                    val params = HashMap<String, String>()
                    params.put("titulo", titulo?.text.toString())
                    params.put("cuerpo", cuerpo?.text.toString())
                    params.put("usuario", "0")
                    Log.e("params", "$params")
                    return params
                }
            }
            queue.add(resultPost)
        }

        return ll
    }
}