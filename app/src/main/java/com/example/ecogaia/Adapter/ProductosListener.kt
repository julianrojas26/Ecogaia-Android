package com.example.ecogaia.Adapter

import org.json.JSONObject

interface ProductosListener {
    fun onProductosCliked(productos: JSONObject,position: Int){
    }
}