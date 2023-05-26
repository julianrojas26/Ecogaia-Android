package com.example.ecogaia.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class PerfilAdapter(private val perfilList: ArrayList<JSONObject>) :
    RecyclerView.Adapter<PerfilAdapter.ViewHoler>() {
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        var usu_nombre: TextView = view.findViewById(R.id.usu_nombre)
        var usu_correo: TextView = view.findViewById(R.id.usu_correo)
        var usu_direccion: TextView = view.findViewById(R.id.usu_direccion)
        var usu_contraseña: TextView = view.findViewById(R.id.usu_contraseña)
        var usu_telefono: TextView = view.findViewById(R.id.usu_telefono)

        fun bind(usuario: JSONObject) {
            usu_nombre.text = usuario.getString("usu_nombre")
            usu_correo.text = usuario.getString("usu_correo")
            usu_direccion.text = usuario.getString("usu_direccion")
            usu_contraseña.text = usuario.getString("usu_contrasenia")
            usu_telefono.text = usuario.getString("usu_telefono")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHoler(
        LayoutInflater.from(parent.context).inflate(R.layout.item_perfil, parent, false)
    )

    override fun getItemCount() = this.perfilList.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val tips = perfilList[position]
        try {
            holder.bind(tips)
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }

}