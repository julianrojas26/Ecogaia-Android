package com.example.ecogaia.adapter

import android.widget.GridView
import org.json.JSONObject

interface ProductosListener {
    fun onProductosCliked(productos: JSONObject,position: Int){
    }
}