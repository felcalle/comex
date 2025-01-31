package com.alura.comex;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProcesadorDeJSON {

    public static List<Pedido> processJson(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("❌ Error: El archivo JSON no existe en la ruta especificada: " + filePath);
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Pedido> pedidoList = objectMapper.readValue(file, new TypeReference<List<Pedido>>() {});
            System.out.println("✅ JSON procesado con éxito. Lista de personas:");
            for (Pedido p : pedidoList) {
                System.out.println(p);
            }
            return pedidoList;
        } catch (Exception e) {
            System.out.println("❌ Error al procesar JSON: " + e.getMessage());
            return null;
        }
    }
}
