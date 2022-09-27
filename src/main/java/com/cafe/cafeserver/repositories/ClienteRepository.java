package com.cafe.cafeserver.repositories;

import com.cafe.cafeserver.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    @Query("SELECT c FROM Cliente c WHERE c.apellido LIKE CONCAT (:apellido,'%')")
    List<Cliente> findByApellido(@Param("apellido") String apellido);

}