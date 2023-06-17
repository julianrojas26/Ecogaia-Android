package com.example.ecogaia.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
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

class fragment_detalle_carrito : DialogFragment() {
    private lateinit var tbProdDets: Toolbar
    private lateinit var nombre_prod: TextView
    private lateinit var cantidad: TextView
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
        val ll = inflater.inflate(R.layout.fragment_detalle_carrito, container, false)

        this.tbProdDets = ll.findViewById(R.id.tbCarritoDets)
        this.tbProdDets.setNavigationOnClickListener {
            dismiss()
            val fragmentManager = parentFragmentManager
            val myFragment = fragment_carrito()
            val fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_carrito, myFragment)
            fragmentTransaction.commit()
        }

        this.nombre_prod = ll.findViewById(R.id.nombre_produ)
        this.cantidad = ll.findViewById(R.id.cantidad_carrito_detalle)
        this.precio_prod = ll.findViewById(R.id.total_carrito_detalle)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url").toString()
        val  user = JSONObject(bundle!!.getString("user"))

        this.tbProdDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)
        val tips = JSONObject(arguments?.getString("carrito"))

        this.nombre_prod.text = tips.getString("Prod_Nombre")
        this.cantidad.text = tips.getString("cantidad")
        this.precio_prod.text = tips.getString("total")

        val add:ImageButton = view.findViewById(R.id.mas)
        add.setOnClickListener() {
            val url = ip + "sumarCarrito/"+ user.getString("res") + "/" + tips.getString("codigo_carrito")
            val queue = Volley.newRequestQueue(this.context)

            val postRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
                    var cantidadNueva = this.cantidad.text.toString().toInt() + 1;
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                }
            )
            queue.add(postRequest)
        }
        val remove:ImageButton = view.findViewById(R.id.menos)
        remove.setOnClickListener() {
            Toast.makeText(this.context, "Menos", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }

}