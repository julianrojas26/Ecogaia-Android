package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Distribuir;
import com.example.Ecogaia.Servicios.ServicioDistribuir;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ControladorDistribuir {
    ServicioDistribuir sd = new ServicioDistribuir();
    @GetMapping("/BuscarDistribuir")
    public ArrayList<Distribuir> buscar() {
        return sd.BuscarDistribuir();
    }
}
