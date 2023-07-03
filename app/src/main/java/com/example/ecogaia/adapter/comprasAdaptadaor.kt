package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class comprasAdaptadaor(
    private val comprasList: ArrayList<JSONObject>,
    private val comprasListener: comprasListener,
) : RecyclerView.Adapter<comprasAdaptadaor.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fecha: TextView = view.findViewById(R.id.fecha_compra)
        val repartidor: TextView = view.findViewById(R.id.repartidor_compra)
        val estado: TextView = view.findViewById(R.id.estado_compra)
        fun bind(compra: JSONObject) {
            fecha.text = compra.getString("venta_fecha")
            repartidor.text = compra.getString("rep_nombre")
            estado.text = compra.getString("venta_estado")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_historial, parent, false)
    )

    override fun getItemCount() = this.comprasList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carrito = comprasList[position]
        try {
            holder.bind(carrito)
            holder.itemView.setOnClickListener {
                comprasListener.onHistorialClicked(carrito, position)
            }
        } catch (e: Exception) {
            Log.w("Error", e)
        }
    }
}