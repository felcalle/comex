package com.alura.comex;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class ProcesadorDeXML {
    public static void main(String[] args) {
        try {
            // Configuración del mapper XML
            XmlMapper xmlMapper = new XmlMapper();

            // Configurar el formato de fecha (si es necesario)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            xmlMapper.setDateFormat(new java.text.SimpleDateFormat("dd/MM/yyyy"));

            // Ruta del archivo XML
            File archivoXml = new File("src/main/resources/pedidos.xml");
            ContenedorPedidos contenedor= xmlMapper.readValue(archivoXml, ContenedorPedidos.class);
            // Deserializar el archivo XML a un objeto Pedido

            // Imprimir el objeto deserializado
            System.out.println(contenedor.getPedidos());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
