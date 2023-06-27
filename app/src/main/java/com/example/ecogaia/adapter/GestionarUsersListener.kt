package com.example.ecogaia.adapter

import org.json.JSONObject

interface GestionarUsersListener {
    fun onUserClicked (user: JSONObject, position: Int)
}