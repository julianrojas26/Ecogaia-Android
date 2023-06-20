package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class RepartidorAdaptador(
    private val repList: ArrayList<JSONObject>,
    private val repartidorListener: RepartidorListener,
) : RecyclerView.Adapter<RepartidorAdaptador.ViewHoler>() {
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {

        var dis_nombre: TextView = view.findViewById(R.id.distribuir_usuario)
        var dis_direccion: TextView = view.findViewById(R.id.distribuir_direcion)
        var dis_telefono: TextView = view.findViewById(R.id.distribuir_telefono)


        fun bind(dis: JSONObject) {

            dis_nombre.text = dis.getString("usu_nombre")
            dis_direccion.text = dis.getString("usu_direccion")
            dis_telefono.text = dis.getString("usu_telefono")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHoler(
        LayoutInflater.from(parent.context).inflate(R.layout.item_repartidor, parent, false)
    )

    override fun getItemCount() = this.repList.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val dis = repList[position]
        try {
            holder.bind(dis)
            holder.itemView.setOnClickListener {
                repartidorListener.onRepartidorListener(dis, position)
            }
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }

}