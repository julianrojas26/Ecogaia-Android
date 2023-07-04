package com.example.ecogaia.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class GestionarProdsAdaptador(
    private val gesList: ArrayList<JSONObject>,
    private val gestionarProdsListener: GestionarProdsListener,
) : RecyclerView.Adapter<GestionarProdsAdaptador.ViewHoler>() {
    private var selectedItemPosition: Int = -1
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        var ges_codigo: TextView = view.findViewById(R.id.codigo_gestionar)
        var ges_nombre: TextView = view.findViewById(R.id.nombre_gestionar)
        var ges_categoria: TextView = view.findViewById(R.id.categoria_gestionar)
        var ges_cantidad: TextView = view.findViewById(R.id.cantidad_gestionar)
        var ges_precio: TextView = view.findViewById(R.id.precio_gestionar)
        fun bind(ges: JSONObject) {
            ges_codigo.text = ges.getString("prod_Codigo")
            var nombre: String = ges.getString("prod_Nombre")
            if (nombre.length > 20) {
                ges_nombre.text = nombre.slice(0..19)
            } else {
                ges_nombre.text = nombre
            }
            ges_categoria.text = ges.getString("prod_Categoria")
            ges_cantidad.text = ges.getString("prod_Cantidad")
            ges_precio.text = ges.getString("prod_Precio")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHoler(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gestionar_productos, parent, false)
    )

    override fun getItemCount() = this.gesList.size
    override fun onBindViewHolder(holder: ViewHoler, @SuppressLint("RecyclerView") position: Int) {
        val ges = gesList[position]
        var item = holder.itemView

        if (position == selectedItemPosition) {
            if (item.background == null) {
                item.setBackgroundColor(R.color.black)
            } else {
                item.background = null
            }
        } else {
            item.background = null
        }

        try {
            holder.bind(ges)
            holder.itemView.setOnClickListener {
                gestionarProdsListener.onProdClicked(ges, position)
                selectedItemPosition = position
                notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }

}
