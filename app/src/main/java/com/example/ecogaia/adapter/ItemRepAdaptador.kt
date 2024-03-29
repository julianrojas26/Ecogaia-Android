package com.example.ecogaia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.ecogaia.R
import org.json.JSONObject

class ItemRepAdaptador(

    private val repList: ArrayList<JSONObject>,
    ) : RecyclerView.Adapter<ItemRepAdaptador.ViewHoler>() {
        inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
            var item_dis_nombre: TextView = view.findViewById(R.id.item_dis_nombre)
            var item_dis_cate: TextView = view.findViewById(R.id.item_dis_cate)
            var item_dis_cant: TextView = view.findViewById(R.id.item_dis_cant)
            var item_dis_precio: TextView = view.findViewById(R.id.item_dis_precio)
            var item_dis_imagen: ImageView = view.findViewById(R.id.item_dis_imagen)


            fun bind(rep: JSONObject) {

                var nombre:String = rep.getString("prod_nombre")
                if(nombre.length > 20){
                    item_dis_nombre.text = nombre.slice(0..19)
                }else{
                    item_dis_nombre.text = nombre
                }

                item_dis_cate.text = rep.getString("prod_categoria")
                item_dis_cant.text = rep.getString("prod_cantidad")
                item_dis_precio.text = rep.getString("prod_precio")

                val imageUrl = rep.getString("prod_imagen")

                Glide.with(itemView)
                    .load(imageUrl)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                    .into(this.item_dis_imagen)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ) = ViewHoler (

        LayoutInflater.from(parent.context).inflate(R.layout.item_detalle_repartidor, parent, false)
            )


        override fun getItemCount() = this.repList.size

        override fun onBindViewHolder(holder: ViewHoler, position: Int) {
            val rep = repList[position]
            try {
                holder.bind(rep)

            } catch (e: Exception) {
                Log.w("ERROR", e)
            }
        }

    }
