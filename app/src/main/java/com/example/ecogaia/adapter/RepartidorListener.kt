package com.example.ecogaia.adapter

import org.json.JSONObject

interface RepartidorListener {
    fun onRepartidorListener(rep: JSONObject, position: Int)
}