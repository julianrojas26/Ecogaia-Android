package com.example.ecogaia.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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


class fragment_blog : Fragment(), BlogListener {
    private lateinit var recycler: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlProductList: RelativeLayout
    private lateinit var blog: ArrayList<JSONObject>
    private lateinit var searchView: SearchView
    private lateinit var adapter: BlogAdaptador


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")

        Log.d("ProductosFragment", "Entered to onCreateView")
        val ll = inflater.inflate(R.layout.fragment_blog, container, false)

        val url = ip + "listarTip"
        val queue = Volley.newRequestQueue(this.context)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    blog.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("BLOG", this.blog.toString())
                this.recycler.adapter = BlogAdaptador(this.blog, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)

        this.recycler = ll.findViewById(R.id.recycler_blog)
        this.viewAlpha = ll.findViewById(R.id.view_blogList)
        this.pgbar = ll.findViewById(R.id.pgbar_blogsList)
        this.rlProductList = ll.findViewById(R.id.rl_BlogList)

        this.blog = ArrayList()


        searchView = ll.findViewById(R.id.searchView)


        // Configurar el RecyclerView
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = BlogAdaptador(this.blog, this)
        recycler.adapter = adapter

        // Configurar la barra de búsqueda
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val userInput = newText?.trim() ?: ""
                Log.w("MENSAJE", userInput)
                var url = ip + "tituloTip/" + userInput
                if (userInput.isEmpty() || userInput == "") {
                    url = ip + "listarTip"
                }
                searchBlog(url, userInput)
                return true
            }
        })

        return ll
    }


    fun searchBlog(url: String, input: String) {
        val queue = Volley.newRequestQueue(context)
        var message = view!!.findViewById<TextView>(R.id.messageBlogs)

        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            try {
                var i = 0
                val l = jsonArray.length()
                blog.clear()
                while (i < l) {
                    blog.add(jsonArray[i] as JSONObject)
                    i++
                }
                if (blog.isEmpty()) {
                    message.text = "No se blogs con la busqueda de \"$input\""
                } else {
                    message.text = ""
                }
                Log.d("BLOG2", blog.toString())
                this.recycler.adapter = BlogAdaptador(blog, this)
                recycler.adapter!!.notifyDataSetChanged()
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
            }
        }, { error ->
            Log.w("jsonError", error)
        })
        queue.add(stringRequest)
    }


    override fun onBlogCliked(tips: JSONObject, position: Int) {
        val bundle = bundleOf("tips" to tips.toString())
        findNavController().navigate(
            R.id.fragment_detalle_blog, bundle
        )
    }
}