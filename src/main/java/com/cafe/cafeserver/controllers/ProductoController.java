package com.cafe.cafeserver.controllers;

import com.cafe.cafeserver.models.Producto;
import com.cafe.cafeserver.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {


    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> findProductoById(@PathVariable(value = "id") Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);

        if(producto.isPresent()) {
            return ResponseEntity.ok().body(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public List<Producto> findProductoByNombre(@PathVariable(value = "nombre") String nombre) {
        return  productoRepository.findByNombre(nombre);
    }
}
