package com.alura.comex;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cliente extends InformeSintetico{
    int pedidoPorCliente = 0;

    public Cliente(int totalCategorias) {
        super(totalCategorias);
    }

    public Cliente(BigDecimal ventasTotal, int cantidadProductosVendidos, int pedidosRealizados, Pedido pedidoMasBarato, Pedido pedidoMasCaro, ArrayList<Pedido> totalPedidos) {
        super(ventasTotal, cantidadProductosVendidos, pedidosRealizados, pedidoMasBarato, pedidoMasCaro, totalPedidos);
    }






}
