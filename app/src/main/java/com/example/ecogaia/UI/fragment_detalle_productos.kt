package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import org.json.JSONObject

class fragment_detalle_productos : DialogFragment() {
    private lateinit var tbProdDets: Toolbar
    private lateinit var nombre_prod: TextView
    private lateinit var categoria_prod: TextView
    private lateinit var precio_prod: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_detalle_productos, container, false)

        this.tbProdDets = ll.findViewById(R.id.tbProdDets)
        this.tbProdDets.setNavigationOnClickListener {
            dismiss()
        }

        this.nombre_prod = ll.findViewById(R.id.nombre_produ)
        this.categoria_prod = ll.findViewById(R.id.categoria_produ)
        this.precio_prod = ll.findViewById(R.id.precio_produ)


        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url").toString()
        val  user = JSONObject(bundle!!.getString("user"))

        val Usuario: String = "7"
        this.tbProdDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)

        val tips = JSONObject(arguments?.getString("productos"))

        val queue = Volley.newRequestQueue(this.context)

        this.nombre_prod.text = tips.getString("prod_Nombre")
        this.categoria_prod.text = tips.getString("prod_Categoria")
        this.precio_prod.text = tips.getString("prod_Precio")

        val addCarrito: ImageButton = view.findViewById(R.id.addCar)

        val addFav: ImageButton = view.findViewById(R.id.addFav)

        addCarrito.setOnClickListener() {
            val  url = ip + "insertarCarrito/" + user.getString("res") + "/" + tips.getString("prod_Codigo") + "/1"
            val resultPost = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                }
            ){
            }
            queue.add(resultPost)
        }

        addFav.setOnClickListener() {
            var url = ip + "usuario/"+ user.getString("res")
            val getPost = object : StringRequest (Request.Method.GET, url,
                Response.Listener<String>{ response ->
                    var user = JSONObject(response)
                    val id = user.getString("id_usuario")
                    url = ip + "insertarFavoritos/" + tips.getString("prod_Codigo") + "/" + id

                    val postRequest = StringRequest(Request.Method.POST, url,
                    Response.Listener<String>{ response ->
                        Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
                    }, Response.ErrorListener { error ->
                        Toast.makeText(this.context, error.stackTraceToString(), Toast.LENGTH_LONG).show()
                    })
                    queue.add(postRequest)
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                }
            ){
            }
            queue.add(getPost)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }
}