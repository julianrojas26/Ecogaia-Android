package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.ecogaia.R
import com.example.ecogaia.adapter.FavoritosListener
import org.json.JSONObject

class FavoritosAdaptador(
    private val favoritosList: ArrayList<JSONObject>,
    private val favoritosListener: FavoritosListener,
) : RecyclerView.Adapter<FavoritosAdaptador.ViewHoler>(),
Filterable{

    private var filteredList: ArrayList<JSONObject> = favoritosList


    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        var fav_nombre: TextView = view.findViewById(R.id.nombre_favoritos)
        var fav_cantidad: TextView = view.findViewById(R.id.cantidad_favoritos)
        var fav_precio: TextView = view.findViewById(R.id.precio_favoritos)
        fun bind(favoritos: JSONObject) {
            var nombres: String = favoritos.getString("prod_Nombre")
            if (nombres.length > 18) {
                fav_nombre.text = nombres.slice(0..18) + "..."
            } else {
                fav_nombre.text = nombres
            }
            fav_cantidad.text = favoritos.getString("prod_Cantidad")
            fav_precio.text = favoritos.getString("prod_Precio")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHoler(
        LayoutInflater.from(parent.context).inflate(R.layout.item_favoritos, parent, false)
    )

    override fun getItemCount() = this.favoritosList.size

    override fun onBindViewHolder(holder: FavoritosAdaptador.ViewHoler, position: Int) {
        val favoritos = favoritosList[position]
        try {
            holder.bind(favoritos)
            holder.itemView.setOnClickListener {
                favoritosListener.onFavoritosClicked(favoritos, position)
            }
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = ArrayList<JSONObject>()

                for (jsonObject in favoritosList) {
                    val nombre = jsonObject.optString("prod_Nombre", "")

                    if (nombre.contains(constraint.toString(), true)) {
                        filteredResults.add(jsonObject)
                    }
                }

                val results = FilterResults()
                results.values = filteredResults
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<JSONObject>
                notifyDataSetChanged()
            }
        }
    }

}