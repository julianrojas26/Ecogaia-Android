package com.example.ecogaia.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecogaia.R
import org.json.JSONObject

class BlogAdaptador(
    private val blogList: ArrayList<JSONObject>,
    private val BlogListener: BlogListener,
) : RecyclerView.Adapter<BlogAdaptador.ViewHoler>() {
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        var comp_Usuario: TextView = view.findViewById(R.id.Nombre)
        var cuerpo: TextView = view.findViewById(R.id.informacion)

        fun bind(tips: JSONObject) {
            var info: String = tips.getString("cuerpo")

            if (info.length > 150) {
                cuerpo.text = info.slice(0..150) + "..."
            } else {
                cuerpo.text = info
            }
            comp_Usuario.text = tips.getString("comp_usuario")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHoler(
        LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
    )

    override fun getItemCount() = this.blogList.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val tips = blogList[position]
        try {
            holder.bind(tips)
            holder.itemView.setOnClickListener {
                BlogListener.onBlogListener(tips, position)
            }
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }
    }

}