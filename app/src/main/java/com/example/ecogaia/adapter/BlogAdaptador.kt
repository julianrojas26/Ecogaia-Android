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
import org.json.JSONObject

class BlogAdaptador(
    private val blogList: ArrayList<JSONObject>,
    private val blogListener: BlogListener):
    RecyclerView.Adapter<BlogAdaptador.ViewHoler>(),
    Filterable {

    private var filteredList: ArrayList<JSONObject> = blogList

    inner class ViewHoler(view: View): RecyclerView.ViewHolder(view) {
        var comp_Usuario: TextView = view.findViewById(R.id.Nombre)
        var cuerpo: TextView = view.findViewById(R.id.informacion)

        fun bind(tips: JSONObject) {
            var info: String = tips.getString("cuerpo")

            if (info.length > 150) {
                cuerpo.text = info.slice(0..150) + "..."
            } else {
                cuerpo.text = info
            }
            comp_Usuario.text = tips.getString("titulo")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHoler (
        LayoutInflater.from(parent.context).inflate(R.layout.item_blog, parent, false)
    )

    override fun getItemCount() = this.blogList.size

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val tips = blogList[position]

        try {
            holder.bind(tips)
            holder.itemView.setOnClickListener{
                blogListener.onBlogCliked(tips, position)
            }
        } catch (e: Exception) {
            Log.w("ERROR", "NO")
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = ArrayList<JSONObject>()

                for (jsonObject in blogList) {
                    val nombre = jsonObject.optString("comp_usuario", "")

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