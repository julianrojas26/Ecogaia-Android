package com.example.ecogaia.UI
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import com.example.ecogaia.adapter.comprasAdaptadaor
import com.example.ecogaia.adapter.comprasListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class fragment_historial: Fragment(), comprasListener{
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlhistorialList: RelativeLayout
    private lateinit var compras: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")
        val user = JSONObject(bundle!!.getString("user"))

        val ll = inflater.inflate(R.layout.fragment_historial, container, false)

        val url = ip + "comprasUsuario/" + user.getString("res")
        val queue = Volley.newRequestQueue(this.context)

        Log.w("URL", url)
        val getRequest = StringRequest(Request.Method.GET, url , { response ->
            val jsonArray = JSONArray(response)
            this.compras = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    compras.add(jsonArray[i] as JSONObject)
                    i++
                }
                this.recycler.adapter = comprasAdaptadaor(this.compras, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
        })
        queue.add(getRequest)

        this.recycler = ll.findViewById(R.id.historial_recycler)
        this.viewAlpha = ll.findViewById(R.id.view_historialList)
        this.pgbar = ll.findViewById(R.id.pgbar_historialList)
        this.rlhistorialList = ll.findViewById(R.id.rl_historialList)

        return ll;
    }

    override fun onHistorialClicked(compras: JSONObject, position: Int) {
        val bundle = Bundle()
        bundle.putString("dis", compras.toString())
        findNavController().navigate(
            R.id.fragment_detalle_historial, bundle
        )
    }
}