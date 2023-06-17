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
<<<<<<< HEAD
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.Adapter.GestionarAdaptador
import com.example.ecogaia.Adapter.ItemRepAdaptador
import com.example.ecogaia.Models.tips
=======
>>>>>>> 6c13fc623e83864ec339f958dd0ec9f17785bb50
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

        val ll = inflater.inflate(R.layout.fragment_detalle_repartidor, container, false)

        val url = ip + "listarProducto"
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