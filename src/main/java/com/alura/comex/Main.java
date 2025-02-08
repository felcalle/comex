package com.alura.comex;

import com.alura.service.ProcesadorCSV;
import com.alura.service.ProcesadorDeJSON;
import com.alura.service.ProcesadorDeXML;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.*;

import static com.alura.comex.Procesador.procesarArchivos;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        int option;
        ListaPedidos listaPedidos;
        ArrayList<Pedido> pedidosGenericos = new ArrayList<>();

        pedidosGenericos = procesarArchivos(scanner, pedidosGenericos);

        BigDecimal totalpedido = BigDecimal.ZERO;
        int totalDeProductosVendidos = 0;
        int totalDePedidosRealizados = 0;
        Pedido pedidoMasBarato = null;
        Pedido pedidoMasCaro = null;


        InformeSintetico informe=new InformeSintetico(totalpedido, totalDeProductosVendidos, totalDePedidosRealizados, pedidoMasBarato,pedidoMasCaro, pedidosGenericos);
        int totalProductosVendidos = informe.getCantidadProductosVendidos();
        int totalPedidosRealizados= informe.getPedidosRealizados();
        int totalDeCategorias = informe.getTotalCategorias();
        pedidoMasBarato= informe.getPedidoMasBarato();
        pedidoMasCaro= informe.getPedidoMasCaro();
        totalpedido= informe.getVentasTotal();
        Cliente informeClientes=new Cliente(totalpedido, totalDeProductosVendidos, totalDePedidosRealizados, pedidoMasBarato, pedidoMasCaro, pedidosGenericos);



        System.out.println("#### INFORME DE VALORES TOTALES");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", totalPedidosRealizados);
        System.out.printf("- TOTAL DE PRODUCTOS VENDIDOS: %s\n",totalProductosVendidos );
        System.out.printf("- TOTAL DE CATEGORIAS: %s\n", totalDeCategorias);
        System.out.printf("- MONTO DE VENTAS: %s\n", NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(totalpedido.setScale(2, RoundingMode.HALF_DOWN))); //Pueden cambiar el Locale a la moneda de su pais, siguiendo esta documentación: https://www.oracle.com/java/technologies/javase/java8locales.html
        System.out.printf("- PEDIDO MAS BARATO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(pedidoMasBarato.getPrecio().multiply(new BigDecimal(pedidoMasBarato.getCantidad())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMasBarato.getProducto());
        System.out.printf("- PEDIDO MAS CARO: %s (%s)\n", NumberFormat.getCurrencyInstance(new Locale("es", "AR")).format(pedidoMasCaro.getPrecio().multiply(new BigDecimal(pedidoMasCaro.getCantidad())).setScale(2, RoundingMode.HALF_DOWN)), pedidoMasCaro.getProducto());
    }



}
