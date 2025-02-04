package com.alura.comex;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class ProcesadorDeXML {

    private static final String FILE_PATH = "src/main/resources/pedidos.xml";

    // Serializa una lista de pedidos a XML
    public static void serializeToXml(ListaPedidos listaPedidos) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(FILE_PATH), listaPedidos);
        System.out.println("Lista de pedidos serializada a " + FILE_PATH);
    }

    // Deserializa el XML a una lista de pedidos
    public static ListaPedidos deserializeFromXml() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(new File(FILE_PATH), ListaPedidos.class);
    }
}