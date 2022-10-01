package com.cafe.cafeserver.controllers;

import com.cafe.cafeserver.models.Cliente;
import com.cafe.cafeserver.models.Pedido;
import com.cafe.cafeserver.models.PedidoProductos;
import com.cafe.cafeserver.models.Producto;
import com.cafe.cafeserver.repositories.ClienteRepository;
import com.cafe.cafeserver.repositories.PedidoProductosRepository;
import com.cafe.cafeserver.repositories.PedidoRepository;
import com.cafe.cafeserver.repositories.ProductoRepository;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import org.springframework.boot.json.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoProductosRepository pedidoProductosRepository;



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
    public String storePedido(@Validated @RequestBody String json){


        try {


            JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);

            Optional<Cliente> cliente = clienteRepository.findById(Integer.parseInt(convertedObject.get("cliente_id").getAsString()));

            if (!cliente.isPresent()) return "cliente_not_present";
            else {

                Pedido pedido = new Pedido();
                pedido.setCliente(cliente.get());
                pedido.setFecha_hora(new Date());

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
                pedido.setFecha_hora(formatter.parse(convertedObject.get("fecha_hora").getAsString()));
                pedido.setEntregado(Boolean.parseBoolean(convertedObject.get("entregado").getAsString()));




                JsonArray ppArray = convertedObject.getAsJsonArray("pedidoProductos");
                List<PedidoProductos> ppL = new ArrayList<PedidoProductos>();


                for (int i=0; i<ppArray.size(); i++) {
                    JsonObject o = ppArray.get(i).getAsJsonObject();

                    PedidoProductos pp = new PedidoProductos();


                    Optional<Producto> p = productoRepository.findById(o.get("id").getAsInt());
                    pp.setProducto(p.get());
                    pp.setCantidad(o.get("cantidad").getAsInt());
                    pp.setPrecioTotal(p.get().getPrecio() * pp.getCantidad());
                    ppL.add(pp);
                }

                ppL = pedidoProductosRepository.saveAll(ppL);
                pedido.setPedidoProductoses(new HashSet<>(ppL));
                pedido = pedidoRepository.saveAndFlush(pedido);

                System.out.println(pedido.getId());

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return "ok";
    }


}
