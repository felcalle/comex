package com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.alura.comex.Pedido.isMasCaroQue;
import static com.alura.comex.ProcesadorDeCsv.extracted;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        extracted(pedidos);
        BigDecimal totalpedido = BigDecimal.ZERO;
        int totalDeProductosVendidos = 0;
        int totalDePedidosRealizados = 0;
        Pedido pedidoMasBarato = null;
        Pedido pedidoMasCaro = null;

        InformeSintetico informe=new InformeSintetico(totalpedido, totalDeProductosVendidos, totalDePedidosRealizados, pedidoMasBarato,pedidoMasCaro, pedidos);
        int totalProductosVendidos = informe.getCantidadProductosVendidos();
        int totalPedidosRealizados= informe.getTotalCategorias();
        int totalDeCategorias = informe.getTotalCategorias();

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoActual = pedidos.get(i);
            if (pedidoActual == null) {
                break;
            }
            pedidoMasBarato = Pedido.isMasBaratoQue(pedidoMasBarato, pedidoActual);
            pedidoMasCaro = isMasCaroQue(pedidoMasCaro, pedidoActual);
            totalpedido= pedidoActual.getValorTotal(pedidoActual, totalpedido);
        }

        System.out.println("#### INFORME DE VALORES TOTALES");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", totalPedidosRealizados);
        System.out.printf("- TOTAL DE PRODUCTOS VENDIDOS: %s\n",totalProductosVendidos );
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
        System.out.printf("- MONTO DE VENTAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(totalpedido.setScale(2, RoundingMode.HALF_DOWN))); //Pueden cambiar el Locale a la moneda de su pais, siguiendo esta documentación: https://www.oracle.com/java/technologies/javase/java8locales.html
        System.out.printf("- PEDIDO MAS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(pedidoMasBarato.getPrecio().multiply(new BigDecimal(pedidoMasBarato.getCantidad())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMasBarato.getProducto());
        System.out.printf("- PEDIDO MAS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(pedidoMasCaro.getPrecio().multiply(new BigDecimal(pedidoMasCaro.getCantidad())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMasCaro.getProducto());
    }
}
