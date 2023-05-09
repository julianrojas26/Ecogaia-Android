package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecogaia.R
import org.json.JSONObject
import java.text.FieldPosition

class CarritoAdaptador(private val carritoList: ArrayList<JSONObject>, private val CarritoListener: CarritoListener): RecyclerView.Adapter<CarritoAdaptador.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var cot_fecha: TextView = view.findViewById(R.id.carrito_fecha)
        var cant_producto: TextView = view.findViewById(R.id.cantidad)

        fun bind(carrito: JSONObject){
            cot_fecha.text = carrito.getString("cot_fecha")
            cant_producto.text = carrito.getString("cant_producto")

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
    )

    override fun getItemCount() = this.carritoList.size

    override  fun onBindViewHolder(holder: ViewHolder, position: Int){
        val carrito = carritoList[position]
        try{
            holder.bind(carrito)

            holder.itemView.setOnClickListener{
                CarritoListener.onCarritoCliked(carrito, position)
            }

        }catch (e: Exception){
            Log.w("imagen_producto", "No se cargo la imagen")
        }
    }
}