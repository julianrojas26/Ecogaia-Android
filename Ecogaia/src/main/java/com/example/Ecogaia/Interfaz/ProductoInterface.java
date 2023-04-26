package com.example.Ecogaia.Interfaz;

import com.example.Ecogaia.Entidades.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoInterface extends CrudRepository<Producto, Integer> {

}
