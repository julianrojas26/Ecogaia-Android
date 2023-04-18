package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Cotizacion;
import com.example.Ecogaia.Servicios.ServicioCotizacion;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ControladorCotizacion {
    ServicioCotizacion sc = new ServicioCotizacion();

    @GetMapping("/BuscarCotizacion")
    public ArrayList<Cotizacion> buscar() {
        return sc.BuscarCotizacion();
    }
}
