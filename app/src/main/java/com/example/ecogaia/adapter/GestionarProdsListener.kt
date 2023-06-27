package com.example.ecogaia.adapter

import org.json.JSONObject

interface GestionarProdsListener {
    fun onProdClicked (prod: JSONObject, position: Int)
}