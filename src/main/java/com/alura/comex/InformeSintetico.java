package com.alura.comex;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.alura.comex.Pedido.isMasCaroQue;

public  class InformeSintetico {

   private BigDecimal ventasTotal = BigDecimal.ZERO;
   private   int cantidadProductosVendidos=0;
   private   int pedidosRealizados = 0;
   private   Pedido pedidoMasBarato = null;
   private Pedido pedidoMasCaro = null;
   private int totalCategorias = 0;
   ArrayList<Pedido> totalPedidos = new ArrayList<>();
   CategoriasProcesadas categoriasProcesadas = new CategoriasProcesadas();

    public InformeSintetico(int totalCategorias) {
        this.totalCategorias = totalCategorias;
    }

    public InformeSintetico(BigDecimal ventasTotal, int cantidadProductosVendidos, int pedidosRealizados, Pedido pedidoMasBarato, Pedido pedidoMasCaro, ArrayList<Pedido> totalPedidos) {
        this.ventasTotal = ventasTotal;
        this.cantidadProductosVendidos = cantidadProductosVendidos;
        this.pedidosRealizados = pedidosRealizados;
        this.pedidoMasBarato = pedidoMasBarato;
        this.pedidoMasCaro = pedidoMasCaro;
        this.totalPedidos = totalPedidos;
    }

    public int getCantidadProductosVendidos() {totalPedidos.forEach(pedido -> cantidadProductosVendidos += pedido.getCantidad()); return cantidadProductosVendidos;}
    public int getPedidosRealizados() {totalPedidos.forEach(pedido -> pedidosRealizados++); return pedidosRealizados;}
    public BigDecimal getVentasTotal() {totalPedidos.forEach(pedido -> ventasTotal=pedido.getValorTotal(pedido, ventasTotal)); return ventasTotal;}
    public Pedido getPedidoMasBarato() {totalPedidos.forEach(pedido -> pedidoMasBarato= Pedido.isMasBaratoQue(pedidoMasBarato, pedido));return pedidoMasBarato;}
    public Pedido getPedidoMasCaro() {totalPedidos.forEach(pedido ->pedidoMasCaro= isMasCaroQue(pedidoMasCaro, pedido) );return pedidoMasCaro;}
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
