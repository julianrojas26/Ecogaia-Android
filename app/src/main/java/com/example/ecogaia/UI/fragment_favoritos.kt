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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.adapter.FavoritosAdaptador
import com.example.ecogaia.adapter.FavoritosListener
import com.example.ecogaia.R
import com.example.ecogaia.adapter.DialogListener
import com.example.ecogaia.adapter.ProductosAdaptador
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class fragment_favoritos : Fragment(), FavoritosListener, DialogListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlProductList: RelativeLayout
    private lateinit var favoritos: ArrayList<JSONObject>
    private lateinit var searchView: SearchView
    private lateinit var adapter: FavoritosAdaptador
    private lateinit var dropdownContentLayoutFav: LinearLayout
    private lateinit var dropdownButtonFav: Button
    private lateinit var OrByNameFav: Button
    private lateinit var OrByPriceFav: Button
    private lateinit var OrByCatFav: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url").toString()
        val user = JSONObject(bundle!!.getString("user"))

        val ll = inflater.inflate(R.layout.fragment_favoritos, container, false)

        val url = ip +"favoritosUsuario/"+ user.getString("res")
        val queue = Volley.newRequestQueue(this.context)

        dropdownContentLayoutFav = ll.findViewById(R.id.llDropdownContentFav)
        dropdownButtonFav = ll.findViewById(R.id.btnDropdownFav)
        OrByPriceFav = ll.findViewById(R.id.OrByPriceFav)
        OrByNameFav = ll.findViewById(R.id.OrByNameFav)
        OrByCatFav = ll.findViewById(R.id.OrByCatFav)

        OrByNameFav.setOnClickListener(){
            val url = ip + "ordenarFavNombre/" + user.getString("res")
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                val jsonArray = JSONArray(response)
                this.favoritos.clear()
                try {
                    var i = 0
                    val l = jsonArray.length()
                    while (i < l) {
                        favoritos += (jsonArray[i] as JSONObject)
                        i++
                    }
                    Log.d("FAVORITOS", this.favoritos.toString())
                    this.recycler.adapter= FavoritosAdaptador(this.favoritos, this)
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
        OrByCatFav.setOnClickListener(){
            val url = ip + "ordenarFavCategoria/" + user.getString("res")
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                val jsonArray = JSONArray(response)
                this.favoritos.clear()
                try {
                    var i = 0
                    val l = jsonArray.length()
                    while (i < l) {
                        favoritos += (jsonArray[i] as JSONObject)
                        i++
                    }
                    Log.d("FAVORITOS", this.favoritos.toString())
                    this.recycler.adapter= FavoritosAdaptador(this.favoritos, this)
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
        OrByPriceFav.setOnClickListener(){
            val url = ip + "ordenarFavPrecio/" + user.getString("res")
            val stringRequest = StringRequest(Request.Method.GET, url, { response ->
                val jsonArray = JSONArray(response)
                this.favoritos.clear()
                try {
                    var i = 0
                    val l = jsonArray.length()
                    while (i < l) {
                        favoritos += (jsonArray[i] as JSONObject)
                        i++
                    }
                    Log.d("FAVORITOS", this.favoritos.toString())
                    this.recycler.adapter= FavoritosAdaptador(this.favoritos, this)
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

        dropdownButtonFav.setOnClickListener {
            toggleDropdownFav()
        }



        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.favoritos = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    favoritos.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("FAVORITOS", this.favoritos.toString())
                this.recycler.adapter = FavoritosAdaptador(this.favoritos, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.favoritos_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_favoritosList)
        this.pgbar = ll.findViewById(R.id.pgbar_favoritosList)
        this.rlProductList = ll.findViewById(R.id.rl_FavoritosList)

        this.favoritos = ArrayList()

        searchView = ll.findViewById(R.id.searchViewFavoritos)


        // Configurar el RecyclerView
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavoritosAdaptador(this.favoritos, this)
        recycler.adapter = adapter

        // Configurar la barra de búsqueda
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
                searchFav(url)
                return true
            }
        })

        return ll
    }


    fun searchFav(url: String) {
        val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            try {
                var i = 0
                val l = jsonArray.length()
                favoritos.clear()
                while (i < l) {
                    favoritos.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("FAV2", favoritos.toString())
                this.recycler.adapter = FavoritosAdaptador(favoritos, this)
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





    override fun onFavoritosClicked(favoritos: JSONObject, position: Int) {
        val bundle = Bundle()
        bundle.putString("productos", favoritos.toString())
        val dialog = fragment_detalle_productos()
        dialog.setDialogListener(this)
        dialog.arguments = bundle
        dialog.show(childFragmentManager.beginTransaction(), "dialog")
    }

    override fun onDialogClosed() {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url").toString()
        val user = JSONObject(bundle!!.getString("user"))

        val url = ip +"favoritosUsuario/"+ user.getString("res")
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.favoritos.clear()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    favoritos.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("FAVORITOS", this.favoritos.toString())
                this.recycler.adapter = FavoritosAdaptador(this.favoritos, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
    }
    private fun toggleDropdownFav() {
        if (dropdownContentLayoutFav.visibility == View.VISIBLE) {
            dropdownContentLayoutFav.visibility = View.GONE
        } else {
            dropdownContentLayoutFav.visibility = View.VISIBLE
        }
    }

}