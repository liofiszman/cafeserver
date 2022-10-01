package com.cafe.cafeserver.repositories;

import com.cafe.cafeserver.models.PedidoProductos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoProductosRepository extends JpaRepository<PedidoProductos, Integer>, JpaSpecificationExecutor<PedidoProductos> {
}
