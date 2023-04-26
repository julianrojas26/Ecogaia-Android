package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.Interfaz.ProductoInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServicioProducto {
    ProductoInterface repository;

    public ServicioProducto(ProductoInterface repository){
        this.repository = repository;
    }
    public String IngresarProducto(Producto p) {
       repository.save(p);
       return "Se agrego el producto";
    }

    public ArrayList<Producto> listar () {
        return (ArrayList<Producto>) repository.findAll();
    }

    public String eliminar(Integer id) {
        String ms = "No se elimino";
        if (repository.existsById(id)){
            repository.deleteById(id);
            ms = "Se elimino";
        }
        return ms;
    }

}

