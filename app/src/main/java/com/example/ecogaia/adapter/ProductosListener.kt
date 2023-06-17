package com.example.ecogaia.adapter

import org.json.JSONObject

interface ProductosListener {
    fun onProductosCliked(productos: JSONObject,position: Int){
    }
}