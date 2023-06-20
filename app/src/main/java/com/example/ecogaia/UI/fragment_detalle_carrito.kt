package com.example.ecogaia.UI

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import org.json.JSONObject

class fragment_detalle_carrito () : DialogFragment() {
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
        }

        this.nombre_prod = ll.findViewById(R.id.nombre_produ)
        this.cantidad = ll.findViewById(R.id.cantidad_carrito_detalle)
        this.precio_prod = ll.findViewById(R.id.total_carrito_detalle)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = activity!!.intent!!.extras!!
        val ip = bundle!!.getString("url").toString()

        this.tbProdDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)
        val tips = JSONObject(arguments!!.getString("carrito"))

        this.nombre_prod.text = tips.getString("Prod_Nombre")
        this.cantidad.text = tips.getString("cantidad")
        this.precio_prod.text = tips.getString("total")

        val add:ImageButton = view.findViewById(R.id.mas)
        add.setOnClickListener() {
            val url = ip + "sumarCarrito/"+ tips.getString("codigo_carrito")
            val queue = Volley.newRequestQueue(this.context)

            val postRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    if (response == "true") {
                        Toast.makeText(this.context, "Se añadio una unidad de " + tips.getString("Prod_Nombre"), Toast.LENGTH_LONG).show()
                        val cantidadNueva = this.cantidad.text.toString().toInt() + 1;
                        var precioNuevo = this.precio_prod.text.toString().toInt();
                        this.precio_prod.text = (precioNuevo + tips.getString("total").toString().toInt()).toString();
                        this.cantidad.text = cantidadNueva.toString();
                    } else if (response == "false") {
                        Toast.makeText(this.context, "El producto no tiene mas unidades disponibles", Toast.LENGTH_LONG).show()
                    }
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                }
            )
            queue.add(postRequest)
        }
        val remove:ImageButton = view.findViewById(R.id.menos)
        remove.setOnClickListener() {
            val url = ip + "restarCarrito/"+ tips.getString("codigo_carrito")
            val queue = Volley.newRequestQueue(this.context)

            val postRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    if (response == "true") {
                        Toast.makeText(this.context, "Se elimino una unidad de " + tips.getString("Prod_Nombre"), Toast.LENGTH_LONG).show()
                        val cantidadNueva = this.cantidad.text.toString().toInt() - 1;
                        var precioNuevo = this.precio_prod.text.toString().toInt();
                        this.precio_prod.text = (precioNuevo + tips.getString("total").toString().toInt()).toString();
                        this.cantidad.text = cantidadNueva.toString();
                    } else if (response == "false") {
                        Toast.makeText(this.context, "Producto eliminado de carrito", Toast.LENGTH_LONG).show()
                    }
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                }
            )
            queue.add(postRequest)
        }
    }



    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDestroy()
        findNavController().navigate(
            R.id.fragment_carrito
        )
    }
}