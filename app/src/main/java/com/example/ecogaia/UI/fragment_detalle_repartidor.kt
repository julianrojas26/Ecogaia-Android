package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.adapter.GestionarAdaptador
import com.example.ecogaia.adapter.ItemRepAdaptador
import com.example.ecogaia.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class fragment_detalle_repartidor : Fragment() {


    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlReptList: RelativeLayout
    private lateinit var itemRepartidor: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")
        val user = JSONObject(bundle!!.getString("user"))

        val dis = JSONObject(arguments?.getString("dis"))

        val ll = inflater.inflate(R.layout.fragment_detalle_repartidor, container, false)

        var usu_nombre: TextView = ll.findViewById(R.id.detalle_rep_usu)
        usu_nombre.text = dis.getString("usu_nombre")
        var usu_direccion: TextView = ll.findViewById(R.id.detale_rep_direccion)
        usu_direccion.text = dis.getString("usu_direccion")
        var usu_telefono: TextView = ll.findViewById(R.id.detalle_rep_telefono)
        usu_telefono.text = dis.getString("usu_telefono")

        val url = ip + "ventasRepartidor/" + user.getString("res") + "/" + dis.getString("id_Usuario")
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this. itemRepartidor= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    itemRepartidor.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("REPARTIDOR", this.itemRepartidor.toString())
                this.recycler.adapter= ItemRepAdaptador(this.itemRepartidor)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.recycler_item_repartidor)
        this.viewAlpha = ll.findViewById(R.id.view_itemRepList)
        this.pgbar = ll.findViewById(R.id.pgbar_itemRepList)
        this.rlReptList = ll.findViewById(R.id.rl_itemRepList)
        return ll
    }

}