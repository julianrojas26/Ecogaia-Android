package com.example.ecogaia.Adapter

import org.json.JSONObject

interface BlogListener {
    fun onBlogCliked(tips: JSONObject, position: Int) {
    }
}