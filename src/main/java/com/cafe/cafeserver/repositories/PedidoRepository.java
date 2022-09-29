package com.cafe.cafeserver.repositories;

import com.cafe.cafeserver.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>, JpaSpecificationExecutor<Pedido> {

    @Query("SELECT p FROM Pedido p WHERE p.cliente.apellido LIKE CONCAT (:apellido,'%')")
    List<Pedido> findByApellido(@Param("apellido") String apellido);

}