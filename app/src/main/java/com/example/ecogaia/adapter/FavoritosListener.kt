package com.example.ecogaia.adapter

import org.json.JSONObject

interface FavoritosListener {
    fun onFavoritosClicked(favoritos: JSONObject, position: Int) {

    }
}