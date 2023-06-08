package com.example.ecogaia.Adapter

import org.json.JSONObject

interface FavoritosListener {
    fun onFavoritosClicked(favoritos: JSONObject, position: Int) {
    }
}