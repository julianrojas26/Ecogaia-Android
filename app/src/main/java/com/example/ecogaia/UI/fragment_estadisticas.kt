package com.example.ecogaia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.adapter.EstadisticasAdaptor
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class fragment_estadisticas : Fragment() {
    private lateinit var recyclerMas: RecyclerView
    private lateinit var recyclerMenos: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlGestList: RelativeLayout
    private lateinit var barChart: BarChart
    private lateinit var estadisticas: ArrayList<JSONObject>
    private lateinit var data: BarData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")

        val ll = inflater.inflate(R.layout.fragment_estadisticas, container, false)
        val queue = Volley.newRequestQueue(this.context)

        //// grafica ventas
        val date = Calendar.getInstance()
        this.barChart = ll.findViewById(R.id.bar_chart)
        this.data = BarData()
        val entries: MutableList<BarEntry> = mutableListOf()
        for (i in 0 until 4) {
            val url = ip + "ventasAnuales/" + (date.get(Calendar.YEAR)-i).toString() + "-01-01" + "/" + (date.get(Calendar.YEAR)-i+1).toString() + "-01-01"
            val StringRequest = StringRequest(Request.Method.GET, url , { response ->
                entries.add(BarEntry(i.toFloat(), response.toFloat()))
                val BarDataSet = BarDataSet(entries, (date.get(Calendar.YEAR)-i).toString())
                addBarEntry(BarDataSet)
            }, { error ->
                Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
            })
            queue.add(StringRequest)
        }
        this.recyclerMas = ll.findViewById(R.id.recycler_top_mas)
        this.recyclerMenos = ll.findViewById(R.id.recycler_top_menos)
        this.viewAlpha = ll.findViewById(R.id.view_estadisticasList)
        this.pgbar = ll.findViewById(R.id.pgbar_estadisticasList)
        this.rlGestList = ll.findViewById(R.id.rl_EstadisticasList)

        /// tops
        top(this.recyclerMas, ip + "listadoDesc")
        top(this.recyclerMenos, ip + "listadoAsc")

        return ll
    }

    fun addBarEntry (BarDataSet: BarDataSet) {
        this.data.addDataSet(BarDataSet)
        this.barChart.data = data
        this.barChart.description.text = "Descripción del gráfico de barras"
        this.barChart.invalidate()
    }

    fun top (recyclerView: RecyclerView, url: String) {
        val queue = Volley.newRequestQueue(this.context)
        val TopRequest = StringRequest(Request.Method.GET, url, { response ->
            this.estadisticas = ArrayList()
            val jsonArray = JSONArray(response)
            var i = 0
            val l = jsonArray.length()
            while (i < l) {
                estadisticas.add(jsonArray[i] as JSONObject)
                i++
            }
            recyclerView.adapter= EstadisticasAdaptor(this.estadisticas)
            this.viewAlpha.visibility= View.INVISIBLE
            this.pgbar.visibility = View.INVISIBLE
        }, { error ->
            Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
        })

        queue.add(TopRequest)
    }
}