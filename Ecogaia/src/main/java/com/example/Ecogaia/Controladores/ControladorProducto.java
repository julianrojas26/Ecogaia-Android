package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.Servicios.ServicioProducto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorProducto {
    ServicioProducto SP;
    public ControladorProducto(ServicioProducto s){
        this.SP = s;
    }
    @PostMapping("/IngresarProducto")
    public void ingresar(@RequestBody Producto p) {
        System.out.println(p);
    }

    @GetMapping("/listar")
    public ArrayList<Producto> mostrar(){
        return SP.listar();
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id){
        return SP.eliminar(id);
    }

}
