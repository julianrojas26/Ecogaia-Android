package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Comprador;
import com.example.Ecogaia.Servicios.ServicioComprador;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ControladorComprador {
    ServicioComprador sc = new ServicioComprador();

    @GetMapping("/BuscarComprador")
    public ArrayList<Comprador> buscar() {
        return sc.BuscarComprador();
    }
}
