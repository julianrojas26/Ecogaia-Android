package com.example.ecogaia.Adapter

import org.json.JSONObject

interface CarritoListener {
    fun onCarritoCliked(carrito: JSONObject, position: Int) {

    }
}