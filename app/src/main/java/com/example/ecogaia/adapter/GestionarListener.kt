package com.example.ecogaia.adapter

import org.json.JSONObject

interface GestionarListener {
    fun onInvetarioClicked (prod: JSONObject, position: Int)
}