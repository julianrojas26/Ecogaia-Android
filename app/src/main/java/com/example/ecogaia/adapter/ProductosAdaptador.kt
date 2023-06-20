package com.example.ecogaia.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.ecogaia.R
import org.json.JSONObject
import com.example.ecogaia.adapter.ProductosListener

class ProductosAdaptador(
    private val context: Context?,
    private val ProductosList: ArrayList<JSONObject>,
    private val productosListener: ProductosListener
):
    BaseAdapter(), Filterable {
    private var filteredList: ArrayList<JSONObject> = ProductosList


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
            view.setOnClickListener {
                productosListener.onProductosCliked(producto, p0)
                Log.w("PRODUCTOS", producto.toString() + p0.toString())
            }
        } catch (e: Exception) {
            Log.w("ERROR", e)
        }

        return view
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = ArrayList<JSONObject>()

                for (jsonObject in ProductosList) {
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