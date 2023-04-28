package com.example.ecogaia
import com.example.ecogaia.Models.productos
import java.text.FieldPosition

interface productosListener {
    fun onProductosCliked(productos: productos,position: Int){
    }
}