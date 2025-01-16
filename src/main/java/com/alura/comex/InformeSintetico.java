package com.alura.comex;

import java.math.BigDecimal;
import java.util.ArrayList;
import static com.alura.comex.ProcesadorDeCsv.extracted;

public  class InformeSintetico {

    BigDecimal ventasTotal = BigDecimal.ZERO;
    int cantidadProductosVendidos=0;
    int pedidosRealizados = 0;
    Pedido pedidoMasBarato = null;
    Pedido pedidoMasCaro = null;
    int totalCategorias = 0;

    CategoriasProcesadas categoriasProcesadas = new CategoriasProcesadas();
    public InformeSintetico(int totalCategorias) {
        this.totalCategorias = totalCategorias;
    }

    ArrayList<Pedido> totalPedidos = new ArrayList<>();


    public InformeSintetico(BigDecimal ventasTotal, int cantidadProductosVendidos, int pedidosRealizados, Pedido pedidoMasBarato, Pedido pedidoMasCaro, ArrayList<Pedido> totalPedidos) {
        this.ventasTotal = ventasTotal;
        this.cantidadProductosVendidos = cantidadProductosVendidos;
        this.pedidosRealizados = pedidosRealizados;
        this.pedidoMasBarato = pedidoMasBarato;
        this.pedidoMasCaro = pedidoMasCaro;
        this.totalPedidos = totalPedidos;
    }

    public int getCantidadProductosVendidos() {
        for (Pedido pedido : totalPedidos) {
            cantidadProductosVendidos += pedido.getCantidad();
        }
        return cantidadProductosVendidos;
    }


    public int getPedidosRealizados() {
        for (Pedido pedido : totalPedidos) {
            pedidosRealizados++;
        }
        return pedidosRealizados;
    }

    public int getTotalCategorias() {
        for (Pedido pedido : totalPedidos) {
            pedidosRealizados++;
            if (!categoriasProcesadas.contains(pedido.getCategoria())) {
                totalCategorias++;
                categoriasProcesadas.add(pedido.getCategoria());
            }
        }
        return totalCategorias;
    }


}
