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

class FavoritosAdaptador(
    private val favoritosList: ArrayList<JSONObject>,
    private val FavoritosListener: FavoritosListener
) : RecyclerView.Adapter<FavoritosAdaptador.ViewHoler>() {
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        var Fav_nombre: TextView = view.findViewById(R.id.nombre_produ_favoritos)
        fun bind(favoritos: JSONObject) {
            Fav_nombre.text = favoritos.getString("codigo_favoritos")
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
                FavoritosListener.onFavoritosClicked(favoritos, position)
            }
        } catch (e: Exception) {
            Log.w("ERROR", "NO")
        }
    }

}