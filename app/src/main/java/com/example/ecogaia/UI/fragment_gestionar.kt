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
import com.example.ecogaia.adapter.*
import com.example.ecogaia.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class fragment_gestionar : Fragment(){
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlGestList: RelativeLayout
    private lateinit var gestionar: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")

        val ll = inflater.inflate(R.layout.fragment_gestionar, container, false)

        val url = ip + "listarProducto"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this. gestionar= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    gestionar.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("REPARTIDOR", this.gestionar.toString())
                this.recycler.adapter= GestionarAdaptador(this.gestionar)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.recycler_gestionar)
        this.viewAlpha = ll.findViewById(R.id.view_gestionarList)
        this.pgbar = ll.findViewById(R.id.pgbar_gestionarList)
        this.rlGestList = ll.findViewById(R.id.rl_GestionarList)
        return ll
    }




}