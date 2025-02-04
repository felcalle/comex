package com.alura.comex;

import com.alura.comex.Pedido;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "/src/main/resources/pedidos.xml")
public class ListaPedidos {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Pedido> pedido;

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "ListaPedidos{" +
                "pedido=" + pedido +
                '}';
    }
}