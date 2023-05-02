package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import com.example.ecogaia.adapter.ProductosAdaptador
import com.example.ecogaia.adapter.ProductosListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_productos : Fragment(), ProductosListener {
   private lateinit var recycler: RecyclerView
   private lateinit var viewAlpha: View
   private lateinit var pgbar: ProgressBar
   private lateinit var rlProductList: RelativeLayout
   private lateinit var productos: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ProductosFragment", "Entered to onCreateView")
        val ll = inflater.inflate(R.layout.fragment_productos, container, false)
        val url = "http://192.168.123.2:8080/listar"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.productos = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    productos.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("ProductFragment", this.productos.toString())
                this.recycler.adapter= ProductosAdaptador(this.productos, this)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e:JSONException) {
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