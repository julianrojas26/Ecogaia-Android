package com.example.ecogaia.adapter

import android.view.View
import android.widget.AdapterView
import org.json.JSONObject

interface ProductosListener {
    fun onProductosCliked(productos: JSONObject,position: Int){
    }
}