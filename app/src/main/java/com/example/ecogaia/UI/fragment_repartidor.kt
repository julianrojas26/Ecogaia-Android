package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import com.example.ecogaia.adapter.BlogAdaptador
import com.example.ecogaia.adapter.BlogListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class fragment_repartidor : Fragment(), BlogListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlProductList: RelativeLayout
    private lateinit var repartidor: ArrayList<JSONObject>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")

        val ll = inflater.inflate(R.layout.fragment_repartidor, container, false)

        val url = ip + "listarRepartidor"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this. repartidor= ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    repartidor.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("REPARTIDOR", this.repartidor.toString())
                this.recycler.adapter= BlogAdaptador(this.repartidor, this)
                this.viewAlpha.visibility= View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            }catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
        this.recycler = ll.findViewById(R.id.recycler_blog)
        this.viewAlpha = ll.findViewById(R.id.view_blogList)
        this.pgbar = ll.findViewById(R.id.pgbar_blogsList)
        this.rlProductList = ll.findViewById(R.id.rl_BlogList)
        return ll
    }

    override fun onBlogCliked(tips: JSONObject, position: Int) {
        val bundle = bundleOf("tips" to tips.toString())
        findNavController().navigate(
            R.id.fragment_detalle_blog, bundle
        )
    }
}