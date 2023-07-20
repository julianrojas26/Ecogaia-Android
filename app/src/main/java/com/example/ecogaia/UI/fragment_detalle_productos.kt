package com.example.ecogaia.UI

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
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
import org.json.JSONObject
import com.example.ecogaia.UI.fragment_productos
import com.example.ecogaia.adapter.DialogListener
import org.json.JSONArray

class fragment_detalle_productos : DialogFragment() {
    private lateinit var tbProdDets: Toolbar
    private lateinit var nombre_prod: TextView
    private lateinit var categoria_prod: TextView
    private lateinit var imagen_prod: ImageView
    private lateinit var precio_prod: TextView
    private var listener: DialogListener? = null

    fun setDialogListener(dialogListener: DialogListener) {
        listener = dialogListener
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
        val ll = inflater.inflate(R.layout.fragment_detalle_productos, container, false)

        this.tbProdDets = ll.findViewById(R.id.tbProdDets)
        this.tbProdDets.setNavigationOnClickListener {
            dismiss()
        }

        this.nombre_prod = ll.findViewById(R.id.nombre_produ)
        this.categoria_prod = ll.findViewById(R.id.categoria_produ)
        this.precio_prod = ll.findViewById(R.id.precio_produ)
        this.imagen_prod = ll.findViewById(R.id.producto_imagen)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url").toString()
        val  user = JSONObject(bundle!!.getString("user"))

        var addCarrito: ImageButton = view.findViewById(R.id.addCar)
        var addFav: ImageButton = view.findViewById(R.id.addFav)

        this.tbProdDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)

        val prod = JSONObject(arguments?.getString("productos"))

        val queue = Volley.newRequestQueue(this.context)

        val url = ip + "favoritosUsuario/" + user.getString("res")

        val StringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            var i = 0
            val l = jsonArray.length()
            while (i < l) {
                val favs = jsonArray[i] as JSONObject
                /// prod.getString("prod_Codigo")
                if (prod.getString("prod_Codigo") == favs.getString("prod_Codigo")) {
                    var star = R.drawable.solid_star
                    addFav.setImageResource(star)
                }
                i++
            }
        }, { error ->
            Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
        })

        queue.add(StringRequest)

        this.nombre_prod.text = prod.getString("prod_Nombre")
        this.categoria_prod.text = prod.getString("prod_Categoria")
        this.precio_prod.text = prod.getString("prod_Precio")

        val imageUrl = prod.getString("prod_Imagen")

        Glide.with(this)
            .load(imageUrl)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .into(this.imagen_prod)

        addCarrito.setOnClickListener() {
            val  url = ip + "insertarCarrito/" + user.getString("res") + "/" + prod.getString("prod_Codigo") + "/1"
            val resultPost = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> { response ->
                    if (response == "true") {
                        Toast.makeText(this.context, "El producto se agrego a carrito", Toast.LENGTH_LONG).show()
                    } else if (response == "false") {
                        Toast.makeText(this.context, "El producto se elimino de carrito", Toast.LENGTH_LONG).show()
                    }
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
                    url = ip + "insertarFavoritos/" + prod.getString("prod_Codigo") + "/" + id

                    val postRequest = StringRequest(Request.Method.POST, url,
                    Response.Listener<String>{ response ->
                        if (response == "true") {
                            Toast.makeText(this.context, "El producto se agrego a favoritos", Toast.LENGTH_LONG).show()
                            var star = R.drawable.solid_star
                            addFav.setImageResource(star)
                        } else if (response == "false") {
                            Toast.makeText(this.context, "El producto se elimino de favoritos", Toast.LENGTH_LONG).show()
                            var star = R.drawable.un_fav
                            addFav.setImageResource(star)
                        }
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

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener?.onDialogClosed()
    }
}