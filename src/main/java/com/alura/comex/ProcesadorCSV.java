package com.alura.comex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorCSV {

    private String rutaArchivo;

    // Constructor que recibe la ruta del archivo
    public ProcesadorCSV(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    // Método para leer el archivo y devolver la lista de pedidos
    public List<Pedido> leerArchivoCSV() {
        List<Pedido> pedidos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) { // Omitir la cabecera
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length == 6) {
                    try {
                        Pedido pedido = new Pedido(
                                datos[0].trim(),  // Categoría
                                datos[1].trim(),  // Producto
                                Double.parseDouble(datos[2].trim()), // Precio
                                Integer.parseInt(datos[3].trim()),  // Cantidad
                                datos[4].trim(),  // Fecha
                                datos[5].trim()   // Cliente
                        );
                        pedidos.add(pedido);
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato en línea: " + linea);
                        e.printStackTrace(); // Para mejor depuración
                    }
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }

        return pedidos;
    }
}