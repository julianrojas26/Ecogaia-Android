package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.Adapter.FavoritosAdaptador
import com.example.ecogaia.Adapter.FavoritosListener
import com.example.ecogaia.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class fragment_favoritos : Fragment(), FavoritosListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlProductList: RelativeLayout
    private lateinit var favoritos: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url").toString()
        val user = JSONObject(bundle!!.getString("user"))

        val ll = inflater.inflate(R.layout.fragment_favoritos, container, false)
<<<<<<< HEAD
        val id_Usuario = "17"
        val url = "http://10.190.80.156:8080/favoritosUsuario/"+id_Usuario
=======
        val url = ip +"favoritosUsuario/"+ user.getString("res")
>>>>>>> de9b055d9973715ce5de3e0d0a3c4c17a3146b50
        val queue = Volley.newRequestQueue(this.context)

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
        return ll
    }

    override fun onFavoritosClicked(favoritos: JSONObject, position: Int) {
        val bundle = bundleOf("productos" to favoritos.toString())
        findNavController().navigate(
            R.id.fragment_detalleProductos, bundle
        )
    }
}