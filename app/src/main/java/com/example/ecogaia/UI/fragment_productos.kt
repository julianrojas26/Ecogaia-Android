package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.Adapter.ProductosAdaptador
import com.example.ecogaia.Adapter.ProductosListener
import com.example.ecogaia.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_productos : Fragment(), ProductosListener {
   private lateinit var recycler: GridView
   private lateinit var viewAlpha: View
   private lateinit var pgbar: ProgressBar
   private lateinit var rlProductList: RelativeLayout
   private lateinit var productos: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_productos, container, false)

        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")
        val url = ip + "listarProducto"

        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.productos = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    productos += (jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("PRODUCTOS", this.productos.toString())
                this.recycler.adapter= ProductosAdaptador(this.context,this.productos, this)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e:JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.products_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_productsList)
        this.pgbar = ll.findViewById(R.id.pgbar_productsList)
        this.rlProductList = ll.findViewById(R.id.rl_ProductsList)
        return ll
    }

    override fun onProductosCliked(productos: JSONObject, position: Int) {
        val bundle = bundleOf("productos" to productos.toString())
        findNavController().navigate(
            R.id.fragment_detalleProductos, bundle
        )
    }
}