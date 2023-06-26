package com.example.ecogaia

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import java.util.*
import kotlin.collections.ArrayList

class fragment_estadisticas : Fragment() {
    private lateinit var barChart: BarChart
    private lateinit var data: BarData
    val random = Random()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = activity?.intent?.extras
        val ip = bundle!!.getString("url")

        val ll = inflater.inflate(R.layout.fragment_estadisticas, container, false)

        val date = Calendar.getInstance()
        val queue = Volley.newRequestQueue(this.context)

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


        return ll
    }

    fun addBarEntry (BarDataSet: BarDataSet) {
        this.data.addDataSet(BarDataSet)
        this.barChart.data = data
        this.barChart.description.text = "Descripción del gráfico de barras"
        this.barChart.invalidate()
    }
}