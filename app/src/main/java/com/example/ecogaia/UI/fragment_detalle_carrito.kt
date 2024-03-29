package com.example.ecogaia.UI

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.ecogaia.R
import com.example.ecogaia.adapter.CarritoListener
import com.example.ecogaia.adapter.DialogListener
import org.json.JSONObject

class fragment_detalle_carrito () : DialogFragment() {
    private lateinit var tbProdDets: Toolbar
    private lateinit var nombre_prod: TextView
    private lateinit var imagen: ImageView
    private lateinit var cantidad: TextView
    private lateinit var precio_prod: TextView
    private var listener: DialogListener? = null

    fun setDialogListener (dialogListener: DialogListener) {
        this.listener = dialogListener
    }

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
        this.imagen = ll.findViewById(R.id.carrito_imagen)
        this.precio_prod = ll.findViewById(R.id.total_carrito_detalle)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = activity!!.intent!!.extras!!
        val ip = bundle!!.getString("url").toString()

        this.tbProdDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)
        val car = JSONObject(arguments!!.getString("carrito"))

        this.nombre_prod.text = car.getString("Prod_Nombre")
        this.cantidad.text = car.getString("cantidad")
        this.precio_prod.text = car.getString("total")

        val imageUrl = car.getString("prod_Imagen")

        Glide.with(this)
            .load(imageUrl)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .into(this.imagen)

        val add:ImageButton = view.findViewById(R.id.mas)
        add.setOnClickListener() {
            val url = ip + "sumarCarrito/"+ car.getString("codigo_carrito")
            val queue = Volley.newRequestQueue(this.context)

            val postRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    if (response == "true") {
                        Toast.makeText(this.context, "Se añadio una unidad de " + car.getString("Prod_Nombre"), Toast.LENGTH_LONG).show()
                        val cantidadNueva = this.cantidad.text.toString().toInt() + 1;
                        var precioNuevo = this.precio_prod.text.toString().toInt();
                        this.precio_prod.text = (precioNuevo + car.getString("prod_precio").toString().toInt()).toString();
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
            val url = ip + "restarCarrito/"+ car.getString("codigo_carrito")
            val queue = Volley.newRequestQueue(this.context)

            val postRequest = StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    if (response == "true") {
                        Toast.makeText(this.context, "Se elimino una unidad de " + car.getString("Prod_Nombre"), Toast.LENGTH_LONG).show()
                        val cantidadNueva = this.cantidad.text.toString().toInt() - 1;
                        var precioNuevo = this.precio_prod.text.toString().toInt();
                        this.precio_prod.text = (precioNuevo - car.getString("prod_precio").toString().toInt()).toString();
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
        listener?.onDialogClosed()
    }
}