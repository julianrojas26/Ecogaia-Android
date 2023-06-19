package com.example.ecogaia.adapter

import org.json.JSONObject

interface BlogListener {
    fun onBlogCliked(tips: JSONObject, position: Int) {
    }
}