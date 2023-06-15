package com.example.ecogaia.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class CarritoAdaptador(
    private val carritoList: ArrayList<JSONObject>,
    private val CarritoListener: CarritoListener,
) : RecyclerView.Adapter<CarritoAdaptador.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cot_cantidad = view.findViewById<TextView>(R.id.cantidad_carrito)
        var cot_nombre = view.findViewById<TextView>(R.id.nombre_carrito)
        var cot_total = view.findViewById<TextView>(R.id.total_carrito)

        fun bind(carrito: JSONObject) {
            cot_cantidad.text = carrito.getString("cantidad")
            cot_nombre.text = carrito.getString("Prod_Nombre")
            cot_total.text = carrito.getString("total")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
    )

    override fun getItemCount() = this.carritoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carrito = carritoList[position]
        try {
            holder.bind(carrito)
            holder.itemView.setOnClickListener {
                CarritoListener.onCarritoCliked(carrito, position)
            }
        } catch (e: Exception) {
            Log.w("imagen_producto", "No se cargo la imagen")
        }
    }
}