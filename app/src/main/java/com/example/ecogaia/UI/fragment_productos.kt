package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.adapter.ProductosAdaptador
import com.example.ecogaia.adapter.ProductosListener
import com.example.ecogaia.R
import com.example.ecogaia.adapter.DialogListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_productos : Fragment(), ProductosListener, DialogListener {
    private lateinit var recycler: GridView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlProductList: RelativeLayout
    private lateinit var productos: ArrayList<JSONObject>
    private lateinit var adapter: ProductosAdaptador
    private lateinit var searchView: SearchView
    private lateinit var dropdownContentLayout: LinearLayout
    private lateinit var dropdownButton: Button
    private lateinit var OrByName: Button
    private lateinit var OrByPrice: Button
    private lateinit var OrByCat: Button



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
        dropdownContentLayout = ll.findViewById(R.id.llDropdownContent)
        dropdownButton = ll.findViewById(R.id.btnDropdown)
        OrByPrice = ll.findViewById(R.id.OrByPrice)
        OrByName = ll.findViewById(R.id.OrByName)
        OrByCat = ll.findViewById(R.id.OrByCat)

        OrByName.setOnClickListener(){
            val url = ip + "ordenarProdNombre"
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                val jsonArray = JSONArray(response)
                this.productos.clear()
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
        }
        OrByCat.setOnClickListener(){
            val url = ip + "ordenarProdCategoria"
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                val jsonArray = JSONArray(response)
                this.productos.clear()
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
        }
        OrByPrice.setOnClickListener(){
            val url = ip + "ordenarProdPrecio"
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                val jsonArray = JSONArray(response)
                this.productos.clear()
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
        }

        dropdownButton.setOnClickListener {
            toggleDropdown()
        }
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

        searchView = ll.findViewById(R.id.search_prod)
        this.productos = ArrayList()
        
        // Configurar el RecyclerView
        adapter = ProductosAdaptador(this.context,this.productos, this)
        recycler.adapter = adapter

        // Configurar la barra de búsqueda
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val userInput = newText?.trim() ?: ""
                var url = ip + "nombreProducto/" + userInput
                if (userInput.isEmpty() || userInput == "") {
                    url = ip + "listarProducto"
                }
                searchProd(url, userInput)
                return true
            }
        })

        return ll
    }

    fun searchProd (url:String, input:String) {

        val queue = Volley.newRequestQueue(context)
        var message = view!!.findViewById<TextView>(R.id.messageProductos)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            try {
                var i = 0
                val l = jsonArray.length()
                productos.clear()
                while (i < l) {
                    productos.add(jsonArray[i] as JSONObject)
                    i++
                }
                if (productos.isEmpty()) {
                    message.text = "No se productos con la busqueda de \"$input\""
                } else {
                    message.text = ""
                }
                Log.d("PROD2", productos.toString())
                this.adapter = ProductosAdaptador(this.context, productos,this)
                adapter.notifyDataSetChanged()
                this.recycler.adapter= adapter
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e: JSONException) {
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
    }

    override fun onProductosCliked(productos: JSONObject, position: Int) {
        val bundle = Bundle()
        bundle.putString("productos", productos.toString())
        val dialog = fragment_detalle_productos()
        dialog.setDialogListener(this)
        dialog.arguments = bundle
        dialog.show(childFragmentManager.beginTransaction(), "dialog")
    }

    override fun onDialogClosed() {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")
        val url = ip + "listarProducto"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.productos.clear()
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
    }

    private fun toggleDropdown() {
        if (dropdownContentLayout.visibility == View.VISIBLE) {
            dropdownContentLayout.visibility = View.GONE
        } else {
            dropdownContentLayout.visibility = View.VISIBLE
        }
    }


}