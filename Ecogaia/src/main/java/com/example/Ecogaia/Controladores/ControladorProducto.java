package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.Servicios.ServicioProducto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ControladorProducto {

    ServicioProducto SP = new ServicioProducto();

    /*@GetMapping("/AgregarProducto")
    public AgregarProducto() {
        return AgregarProducto;
    } */

    @GetMapping("/BuscarProducto")
    public ArrayList<Producto> BuscarProducto() {
        return SP.BuscarProducto;
    }


}
