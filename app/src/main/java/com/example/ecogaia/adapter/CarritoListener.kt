package com.example.ecogaia.adapter

import org.json.JSONObject

interface CarritoListener {
    fun onCarritoCliked(carrito: JSONObject, position: Int) {

    }
}