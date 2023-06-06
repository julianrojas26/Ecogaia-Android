package com.example.ecogaia.Adapter

import org.json.JSONObject

interface BlogListener {
    fun onBlogListener(tips: JSONObject, position: Int) {
    }
}