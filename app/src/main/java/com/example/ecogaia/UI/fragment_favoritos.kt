package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import com.example.ecogaia.adapter.FavoritosAdaptador
import com.example.ecogaia.adapter.FavoritosListener
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
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FavoritosFragment", "Entered to onCreateView")
        val ll = inflater.inflate(R.layout.fragment_favoritos, container, false)
        val url = "http://192.168.252.170:8080/listarFavoritos"
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
                Log.d("FavoritosFragment", this.favoritos.toString())
                this.recycler.adapter= FavoritosAdaptador(this.favoritos, this)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e:JSONException) {
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


}