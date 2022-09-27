package com.cafe.cafeserver.repositories;

import com.cafe.cafeserver.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer>, JpaSpecificationExecutor<Producto> {

    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE CONCAT (:nombre,'%')")
    List<Producto> findByNombre(@Param("nombre") String nombre);
}