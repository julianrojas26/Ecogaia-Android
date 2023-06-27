package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class GestionarUsersAdaptor(
    private val gesList: ArrayList<JSONObject>,
    private val gestionarUsersListener: GestionarUsersListener,
) : RecyclerView.Adapter<GestionarUsersAdaptor.ViewHoler>() {
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {

        var id_usuario: TextView = view.findViewById(R.id.id_usuario)
        var usu_rol: TextView = view.findViewById(R.id.rol_usuario)
        var usu_contrasenia: TextView = view.findViewById(R.id.contrasenia_usuario)
        var usu_correo: TextView = view.findViewById(R.id.correo_usuario)

        fun bind(ges: JSONObject) {

            id_usuario.text = ges.getString("id_Usuario")
            var nombre: String = ges.getString("usu_nombre")
            usu_rol.text = ges.getString("rol")
            usu_contrasenia.text = ges.getString("usu_contrasenia")
            usu_correo.text = ges.getString("usu_correo")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHoler(
        LayoutInflater.from(parent.context).inflate(R.layout.item_gestionar_usuarios, parent, false)
    )

    override fun getItemCount() = this.gesList.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val ges = gesList[position]
        try {
            holder.bind(ges)
            holder.itemView.setOnClickListener {
                gestionarUsersListener.onUserClicked(ges, position)
            }
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }

}