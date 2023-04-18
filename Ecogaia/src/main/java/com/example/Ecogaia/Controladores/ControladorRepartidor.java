package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Repartidor;
import com.example.Ecogaia.Servicios.ServicioRepartidor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ControladorRepartidor {
    ServicioRepartidor sp = new ServicioRepartidor();
    @GetMapping("/BuscarRepartidor")
    public ArrayList<Repartidor> buscar() {
        return sp.BuscarRepartidor();
    }
}
