package com.example.ecogaia.adapter

import org.json.JSONObject

interface BlogListener {
    fun onBlogListener(tips: JSONObject, position: Int) {
    }
}