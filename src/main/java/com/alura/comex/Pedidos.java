package com.alura.comex;

import com.alura.comex.Pedido;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Pedidos {

    private List<Pedido> pedidos;

    @XmlElement(name = "pedido")
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}