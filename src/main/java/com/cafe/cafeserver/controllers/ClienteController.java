package com.cafe.cafeserver.controllers;

import com.cafe.cafeserver.models.Cliente;
import com.cafe.cafeserver.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable(value = "id") Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/apellido/{apellido}")
    public List<Cliente> findClienteByApellido(@PathVariable(value = "apellido") String apellido) {
        return  clienteRepository.findByApellido(apellido);

    }

    @PostMapping
    public Cliente saveUser(@Validated @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }


}
