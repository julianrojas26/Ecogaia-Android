package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class EstadisticasAdaptor (
    private val gesList: ArrayList<JSONObject>,
) : RecyclerView.Adapter<EstadisticasAdaptor.ViewHoler>() {
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        var cantidad: TextView = view.findViewById(R.id.est_cantidad)
        var nombre: TextView = view.findViewById(R.id.est_nombre)

        fun bind(ges: JSONObject) {
            cantidad.text = ges.getString("cantidad")
            nombre.text = ges.getString("prod_Nombre")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHoler(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estadisticas, parent, false)
    )

    override fun getItemCount() = this.gesList.size
    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val ges = gesList[position]
        try {
            holder.bind(ges)
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }

}