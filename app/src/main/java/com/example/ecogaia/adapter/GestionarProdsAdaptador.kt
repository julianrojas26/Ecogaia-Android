package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class GestionarProdsAdaptador(
    private val gesList: ArrayList<JSONObject>,
    private val gestionarProdsListener: GestionarProdsListener,
) : RecyclerView.Adapter<GestionarProdsAdaptador.ViewHoler>() {
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
    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val ges = gesList[position]
        try {
            holder.bind(ges)
            holder.itemView.setOnClickListener {
                gestionarProdsListener.onProdClicked(ges, position)
            }
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }

}
