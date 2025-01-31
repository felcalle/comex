package com.alura.comex;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;

public class ProcesadorDeXML {
    public static Pedido processXml(String filePath) {
        File file = Paths.get(filePath).toFile();

        if (!file.exists() || !file.isFile()) {
            System.err.println("❌ Error: El archivo XML no existe o no es un archivo válido en la ruta: " + filePath);
            return null;
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Pedido.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Pedido pedido = (Pedido) unmarshaller.unmarshal(file);
            System.out.println("✅ XML procesado con éxito. Pedido:");
            return pedido;
        } catch (JAXBException e) {
            System.err.println("❌ Error al procesar XML: " + e.getMessage());
            return null;
        }
    }
}