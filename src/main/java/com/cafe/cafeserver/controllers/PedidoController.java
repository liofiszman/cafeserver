package com.cafe.cafeserver.controllers;

import com.cafe.cafeserver.models.Pedido;
import com.cafe.cafeserver.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> findAllPedidos() {
        return pedidoRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findPedidoById(@PathVariable(value = "id") Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if(pedido.isPresent()) {
            return ResponseEntity.ok().body(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/apellido/{apellido}")
    public List<Pedido> findPedidoByApellido(@PathVariable(value = "apellido") String apellido) {
        return  pedidoRepository.findByApellido(apellido);
    }


    @PostMapping
    public Pedido savePedido(@Validated @RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


}
