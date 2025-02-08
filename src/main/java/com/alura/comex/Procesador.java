package com.alura.comex;

import com.alura.service.ProcesadorCSV;
import com.alura.service.ProcesadorDeJSON;
import com.alura.service.ProcesadorDeXML;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Scanner;

public class Procesador {
    static @Nullable ArrayList<Pedido> procesarArchivos(Scanner scanner, ArrayList<Pedido> pedidosGenericos) {
        ListaPedidos listaPedidos;
        int option;
        do {
            System.out.println("\n SELECCIONA EL TIPO DE ARCHIVO A PROCESAR: ");
            System.out.println("1. Tipo de archivo JSON");
            System.out.println("2. Tipo de archivo CSV");
            System.out.println("3. Tipo de archivo XML");
            System.out.println("4. Salir del programa");

            switch (option = scanner.nextInt()) {
                case 1:
                    pedidosGenericos = (ArrayList<Pedido>) ProcesadorDeJSON.processJson("/app/resources/pedidos.json");
                    System.out.println("Ingresa 5 + Enter para ir a informe");
                    option = scanner.nextInt();
                    break;
                case 2:
                    ProcesadorCSV procesador= new ProcesadorCSV("/app/resources/pedidos.csv");
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
                        pedidosGenericos =new ArrayList<>(listaPedidos.getPedido());
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
        return pedidosGenericos;
    }


    public static void processCsv(String s) {
    }



}
