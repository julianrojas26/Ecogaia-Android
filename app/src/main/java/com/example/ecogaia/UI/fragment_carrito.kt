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
import com.example.ecogaia.adapter.CarritoAdaptador
import com.example.ecogaia.adapter.CarritoListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_carrito : Fragment(), CarritoListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rl_CarritoList: RelativeLayout
    private lateinit var carrito: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("CarritoFragment", "Entered to onCreateView")
        val ll = inflater.inflate(R.layout.fragment_carrito, container, false)
        val url = "http://192.168.136.131:8080/listarCotizacion"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.carrito = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    carrito.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("ProductFragment", this.carrito.toString())
                this.recycler.adapter= CarritoAdaptador(this.carrito, this)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e:JSONException) {
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.carrito_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_carritoList)
        this.pgbar = ll.findViewById(R.id.pgbar_carritoList)
        this.rl_CarritoList = ll.findViewById(R.id.rl_CarritoList)
        return ll
    }

    override fun onCarritoCliked(carrito: JSONObject, position: Int){
        val bundle = bundleOf("carrito" to carrito.toString())
        findNavController().navigate(
            R.id.fragment_detalleProductos, bundle
        )
    }

    fun addProducto() {

    }
}