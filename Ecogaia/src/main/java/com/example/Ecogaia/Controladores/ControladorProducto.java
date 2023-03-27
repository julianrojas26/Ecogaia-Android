package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Servicios.ServicioProducto;
import org.springframework.web.bind.annotation.GetMapping;

public class ControladorProducto {

    ServicioProducto SP = new ServicioProducto();

    @GetMapping("/AgregarProducto")
    public AgregarProducto() {
        return AgregarProducto;
    }
    @GetMapping("/BuscarProducto")
    public BuscarProducto() {
        return BuscarProducto;
    }


}
