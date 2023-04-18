package com.example.Ecogaia.Controladores;

import com.example.Ecogaia.Entidades.Venta;
import com.example.Ecogaia.Servicios.ServicioVenta;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ControladorVenta {
    ServicioVenta sv = new ServicioVenta();
    @GetMapping("/BuscarVentas")
    public ArrayList<Venta> buscar() {
        return sv.BuscarVenta();
    }
}
