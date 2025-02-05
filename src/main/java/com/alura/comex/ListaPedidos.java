package com.alura.comex;

import com.alura.comex.Pedido;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "/src/main/resources/pedidos.xml")
public class ListaPedidos  {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Pedido> pedido; // Lista interna de pedidos

    // Constructor que inicializa la lista
    public ListaPedidos() {
        this.pedido = new ArrayList<>();
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    // Método para agregar un pedido a la lista
    public void agregarPedido(Pedido nuevoPedido) {
        this.pedido.add(nuevoPedido);
    }

    // Método para verificar si la lista está vacía
    public boolean estaVacia() {
        return this.pedido.isEmpty();
    }

    @Override
    public String toString() {
        return "ListaPedidos{" +
                "pedido=" + pedido +
                '}';
    }
}