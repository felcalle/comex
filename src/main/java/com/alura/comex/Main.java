package com.alura.comex;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        int option;
        ListaPedidos listaPedidos;
        ArrayList<Pedido> pedidosGenericos = new ArrayList<>();

        do {
            System.out.println("\n SELECCIONA EL TIPO DE ARCHIVO A PROCESAR: ");
            System.out.println("1. Tipo de archivo JSON");
            System.out.println("2. Tipo de archivo CSV");
            System.out.println("3. Tipo de archivo XML");
            System.out.println("4. Salir del programa");

            switch (option = scanner.nextInt()) {
                case 1:
                    pedidosGenericos= (ArrayList<Pedido>) ProcesadorDeJSON.processJson("src/main/resources/pedidos.json");
                    System.out.println("Ingresa 5 + Enter para ir a informe");
                    option = scanner.nextInt();
                    break;
                case 2:
                    ProcesadorCSV procesador= new ProcesadorCSV("src/main/resources/pedidos.csv");
                    pedidosGenericos.addAll(procesador.leerArchivoCSV());
                    System.out.println("Lista de productos procesados:");
                    for (Pedido producto : pedidosGenericos) {
                        System.out.println(producto);
                    }

                case 3:
                    try {
                        listaPedidos= ProcesadorDeXML.deserializeFromXml();
                        for (Pedido pedido : listaPedidos.getPedido()) {
                            System.out.println(pedido);
                        }
                        pedidosGenericos=new ArrayList<>(listaPedidos.getPedido());
                        System.out.println("Ingresa 5 + Enter para ir a informe");
                        option = scanner.nextInt();
                        break;


                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }


                case 4:
                    System.out.println("Salir del programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (option!=5);

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



    public static void processCsv(String s) {
    }

}
