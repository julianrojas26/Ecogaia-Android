package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.adapter.CarritoAdaptador
import com.example.ecogaia.adapter.CarritoListener
import com.example.ecogaia.R
import com.example.ecogaia.adapter.FavoritosAdaptador
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_carrito : Fragment(), CarritoListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rl_CarritoList: RelativeLayout
    private lateinit var carrito: ArrayList<JSONObject>
    private lateinit var searchView: SearchView
    private lateinit var adapter: CarritoAdaptador


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")
        val user = JSONObject(bundle!!.getString("user"))

        val ll = inflater.inflate(R.layout.fragment_carrito, container, false)

        var btnComprar = ll.findViewById<Button>(R.id.btnComprar)

        val url = ip + "cotizacionesUsuario/" + user.getString("res")
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.carrito = ArrayList()
            var total = 0;
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    carrito.add(jsonArray[i] as JSONObject)
                    total+= carrito[i].getString("total").toInt()
                    i++
                }
                ll.findViewById<TextView>(R.id.carritoTotal).text = total.toString()
                Log.d("CARRITO", this.carrito.toString())
                this.recycler.adapter = CarritoAdaptador(this.carrito, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.carrito_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_carritoList)
        this.pgbar = ll.findViewById(R.id.pgbar_carritoList)
        this.rl_CarritoList = ll.findViewById(R.id.rl_CarritoList)

        this.carrito = ArrayList()

        searchView = ll.findViewById(R.id.Search_carrito)

        // Configurar el RecyclerView
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = CarritoAdaptador(this.carrito, this)
        recycler.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val userInput = newText?.trim() ?: ""
                Log.w("MENSAJE", userInput)
                var url = ip + "NombreFavorito/" + user.getString("res") + "/" + userInput
                if (userInput.isEmpty() || userInput == "") {
                    url =  ip +"favoritosUsuario/"+ user.getString("res")
                }
                searchCarr(url)
                return true
            }
        })


        btnComprar.setOnClickListener() {
            val url = ip + "compra/" + user.getString("res")
            val queue = Volley.newRequestQueue(this.context)

            val postRequets = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
                }, Response.ErrorListener { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                }
            )
            queue.add(postRequets)
        }
        // Configurar la barra de búsqueda



        return ll
    }


    fun searchCarr(url: String) {
        val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            try {
                var i = 0
                val l = jsonArray.length()
                carrito.clear()
                while (i < l) {
                    carrito.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("CAR2", carrito.toString())
                this.recycler.adapter = CarritoAdaptador(carrito, this)
                recycler.adapter!!.notifyDataSetChanged()
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
    }
    override fun onCarritoCliked(carrito: JSONObject, position: Int) {
        val bundle = Bundle()
        bundle.putString("carrito", carrito.toString())
        findNavController().navigate(
            R.id.fragment_detalle_carrito, bundle
        )
    }
}