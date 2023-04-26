package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Comprador;
import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.Servicios.ServicioComprador;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ControladorComprador {
    ServicioComprador sc = new ServicioComprador();

    @GetMapping("/BuscarComprador")
    public ArrayList<Comprador> buscar() {
        return sc.BuscarComprador();
    }

    @PostMapping("/IngresarComprador")
    public Boolean ingresar(@RequestBody Comprador c){
        return sc.IngresarComprador(c);
    }
}
