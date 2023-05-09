package com.example.ecogaia.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.ListAdapter
import com.example.ecogaia.R
import org.json.JSONObject

class ProductosAdaptador(var context: Context?, var ProductosList: ArrayList<JSONObject>, var ProductosListener: ProductosListener) :
    BaseAdapter() {

    override fun getCount(): Int {
        return ProductosList.size
    }

    override fun getItem(p0: Int): Any {
        return ProductosList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View {
        var view: View = View.inflate(context, R.layout.item_producto, null)

        var prod_nombre: TextView = view.findViewById(R.id.nombre_produ_productos)
        var prod_precio: TextView = view.findViewById(R.id.precio_produ_productos)

        var producto: JSONObject = ProductosList.get(p0)


        var nombres: String = producto.getString("prod_Nombre")

        if (nombres.length > 18) {
            prod_nombre.text = nombres.slice(0..18) + "..."
        } else {
            prod_nombre.text = nombres
        }
        prod_precio.text = producto.getString("prod_Precio")

        try {
            view.setOnClickListener{
                ProductosListener.onProductosCliked(producto,p0)
                Log.w("SIIIIII", producto.toString() + p0.toString())
            }
        } catch (e: Exception) {
            Log.w("EEEEEEEEEE", e)
        }

        return view
    }

}