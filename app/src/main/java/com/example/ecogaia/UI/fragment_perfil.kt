package com.example.ecogaia.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.MainActivity
import com.example.ecogaia.R
import com.example.ecogaia.adapter.PerfilAdapter
import com.example.ecogaia.adapter.ProductosAdaptador
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_perfil : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlPerfilList: RelativeLayout
    private lateinit var perfil: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ProductosFragment", "Entered to onCreateView")
        val ll = inflater.inflate(R.layout.fragment_perfil, container, false)
        val url = "http:// 192.168.110.131:8080/listarUsuario"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.perfil = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    perfil += (jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("ProductFragment", this.perfil.toString())
                this.recycler.adapter= PerfilAdapter(this.perfil)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e: JSONException) {
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.recycler_perfil)
        this.viewAlpha = ll.findViewById(R.id.view_perfilList)
        this.pgbar = ll.findViewById(R.id.pgbar_perfilList)
        this.rlPerfilList = ll.findViewById(R.id.rl_perfilList)
        return ll
    }


}