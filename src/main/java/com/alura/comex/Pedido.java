package com.alura.comex;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement
public class Pedido {

    private String categoria;
    private String producto;
    private BigDecimal precio;
    private int cantidad;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    private String cliente;

    // Constructor sin parámetros (para JAXB)
    public Pedido() {}

    // Constructor ideal para el JSON proporcionado
    public Pedido(String categoria, String producto, BigDecimal precio, int cantidad, String fecha, String cliente) {
        this.categoria = categoria;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        // Convertir la fecha del formato "dd/MM/yyyy" a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = LocalDate.parse(fecha, formatter);
        this.cliente = cliente;
    }

    @XmlElement
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @XmlElement
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @XmlElement
    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @XmlElement
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @XmlElement
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = LocalDate.parse(fecha, formatter);
    }

    @XmlElement
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", producto='" + producto + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", cliente='" + cliente + '\'' +
                '}';
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValorTotal(@NotNull Pedido pedido, BigDecimal totalX) {
        totalX = totalX.add(pedido.getPrecio().multiply(new BigDecimal(pedido.getCantidad())));
        return totalX;
    }

    static Pedido isMasBaratoQue(Pedido pedidoMasBarato, Pedido pedidoActual) {

        if (pedidoMasBarato == null || pedidoActual.getPrecio().multiply(new BigDecimal(pedidoActual.getCantidad())).compareTo(pedidoMasBarato.getPrecio().multiply(new BigDecimal(pedidoMasBarato.getCantidad()))) < 0) {
            pedidoMasBarato = pedidoActual;
        }
        return pedidoMasBarato;
    }
    static Pedido isMasCaroQue(Pedido pedidoMasCaro, Pedido pedidoActual) {
        if (pedidoMasCaro == null || pedidoActual.getPrecio().multiply(new BigDecimal(pedidoActual.getCantidad())).compareTo(pedidoMasCaro.getPrecio().multiply(new BigDecimal(pedidoMasCaro.getCantidad()))) > 0) {
            pedidoMasCaro = pedidoActual;
        }
        return pedidoMasCaro;
    }


}