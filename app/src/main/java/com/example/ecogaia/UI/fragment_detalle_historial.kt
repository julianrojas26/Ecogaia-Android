package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.adapter.ItemRepAdaptador
import com.example.ecogaia.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_detalle_historial : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlProductosList: RelativeLayout
    private lateinit var itemProductos: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_detalle_historial, container, false)

        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")
        val user = JSONObject(bundle!!.getString("user"))

        val dis = JSONObject(arguments?.getString("dis"))

        var usu_nombre: TextView = ll.findViewById(R.id.rep_nombre)
        usu_nombre.text = dis.getString("usu_nombre")
        var usu_direccion: TextView = ll.findViewById(R.id.compra_estado)
        usu_direccion.text = dis.getString("venta_estado")
        var usu_telefono: TextView = ll.findViewById(R.id.telefono_compra)
        usu_telefono.text = dis.getString("rep_nombre")

        val url = ip + "productosCompra/" + user.getString("res") + "/" + dis.getString("venta_codigo")
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.itemProductos= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    itemProductos.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("REPARTIDOR", this.itemProductos.toString())
                this.recycler.adapter= ItemRepAdaptador(this.itemProductos)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.recycler_item_historial)
        this.viewAlpha = ll.findViewById(R.id.view_itemCompList)
        this.pgbar = ll.findViewById(R.id.pgbar_itemCompList)
        this.rlProductosList = ll.findViewById(R.id.rl_itemCompList)
        return ll
    }

}