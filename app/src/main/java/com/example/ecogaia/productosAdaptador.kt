package com.example.ecogaia

import android.provider.ContactsContract
import com.example.ecogaia.Models.productos
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewParent
import androidx.appcompat.view.menu.MenuView
import kotlin.collections.ArrayList


class productosAdaptador(val productosListener: productosListener) : RecyclerView.Adapter<productosAdaptador.ViewHolder>(){
    val listProductos = ArrayList<productos>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_detalle_productos, parent,false))
    override fun onBindViewHolder(holder: ViewHolder,position: Int){
        val productos = listProductos[position]
        holder.Prod_precio.text = productos.Prod_Precio
        holder.Prod_Nombre.text = productos.Prod_Nombre
        holder.Prod_Categoria.text = productos.Prod_Categoria
        holder.Prod_Descripcion.text = productos.Prod_Descripcion

        holder.itemView.setOnClickListener{ productosListener.onProductosCliked(productos,position)
        }

    }

    fun updateData(data: List<productos>){
        listProductos.clear()
        listProductos.addAll(data)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val Prod_Nombre = itemView.findViewById<TextView>(R.id.nombre_produ)
        val Prod_Descripcion = itemView.findViewById<TextView>(R.id.descripcion_produ)
        val Prod_Categoria = itemView.findViewById<TextView>(R.id.categoria_produ)
        val Prod_precio = itemView.findViewById<TextView>(R.id.precio_produ)

    }

    override fun getItemCount()  = listProductos.size
}