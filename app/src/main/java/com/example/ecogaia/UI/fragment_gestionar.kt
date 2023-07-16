package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.adapter.*
import com.example.ecogaia.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class fragment_gestionar : Fragment(), GestionarProdsListener{
    private lateinit var recycler_productos: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var rlGestionarList: RelativeLayout
    private lateinit var pgbar: ProgressBar
    private lateinit var gestionarProds: ArrayList<JSONObject>
    private var prodId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")

        val ll = inflater.inflate(R.layout.fragment_gestionar, container, false)

        var url = ip + "listarProducto"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this. gestionarProds= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    gestionarProds.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("REPARTIDOR", this.gestionarProds.toString())
                this.recycler_productos.adapter= GestionarProdsAdaptador(this.gestionarProds, this)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)

        this.recycler_productos = ll.findViewById(R.id.recycler_gestionar_productos)
        this.viewAlpha = ll.findViewById(R.id.view_gestionarList)
        this.pgbar = ll.findViewById(R.id.pgbar_gestionarList)
        this.rlGestionarList = ll.findViewById(R.id.rl_GestionarList)


        val delete_prod = ll.findViewById<Button>(R.id.DeleteProduct)

        delete_prod.setOnClickListener() {
            if (prodId == null) {
                Toast.makeText(this.context, "Debes seleccionar un producto", Toast.LENGTH_LONG).show()
            } else {
                url = ip + "eliminarProducto/" + this.prodId
                val queue = Volley.newRequestQueue(this.context)

                val deleteRequest = StringRequest(Request.Method.DELETE, url, { response ->
                    Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
                }, { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                })

                queue.add(deleteRequest)
            }
        }

        return ll
    }

    override fun onProdClicked (prod: JSONObject, position: Int) {
           this.prodId = prod.getString("prod_Codigo")
    }

}