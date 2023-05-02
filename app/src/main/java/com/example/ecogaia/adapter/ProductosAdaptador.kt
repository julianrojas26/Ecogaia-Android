package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecogaia.R
import org.json.JSONObject


class ProductosAdaptador(private val productList: ArrayList<JSONObject>, private val ProductosListener: ProductosListener): RecyclerView.Adapter<ProductosAdaptador.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var prod_Precio: TextView = view.findViewById(R.id.precio_produ_productos)
        var prod_Nombre: TextView = view.findViewById(R.id.nombre_produ_productos)
        var prod_Imagen: ImageView = view.findViewById(R.id.imagen_produ_productos)

        fun bind(productos: JSONObject) {
            prod_Precio.text = productos.getString("prod_Precio")
            prod_Nombre.text = productos.getString("prod_Nombre")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
    )

    override fun getItemCount() = this.productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productos = productList[position]

        try {
            Glide.with(holder.itemView.context)
                .load(productos.get("prod_Imagen"))
                .into(holder.prod_Imagen)
            holder.bind(productos)

            holder.itemView.setOnClickListener{
                ProductosListener.onProductosCliked(productos, position)
            }
        } catch (e: Exception) {
            Log.w("ProductImagen", "No se cargo la imagen")
        }
    }

}