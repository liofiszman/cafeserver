package com.cafe.cafeserver.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Column(name = "fecha_hora", nullable = false)
    private Date fecha_hora;

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    @Column(name = "entregado", nullable = false)
    private boolean entregado;


    public boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    @OneToMany(orphanRemoval = true)
    @JoinColumn
    private Set<PedidoProductos> pedidoProductoses = new LinkedHashSet<>();

    public Set<PedidoProductos> getPedidoProductoses() {
        return pedidoProductoses;
    }

    public void setPedidoProductoses(Set<PedidoProductos> pedidoProductoses) {
        this.pedidoProductoses = pedidoProductoses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pedido pedido = (Pedido) o;
        return id != null && Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
