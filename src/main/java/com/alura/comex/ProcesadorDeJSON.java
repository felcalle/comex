package com.alura.comex;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProcesadorDeJSON {

    static void extracted(ArrayList<Pedido> pedidos) {
        try {
            URL recursoJSON = ClassLoader.getSystemResource("pedidos.json");
            Path caminoDelArchivo = caminoDelArchivo = Path.of(recursoJSON.toURI());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();

            Collection<?> pedidosLeidos = objectMapper.readValue(
                    caminoDelArchivo.toFile(),
                    new TypeReference<List<Pedido>>() {}
            );

            // Agregar los pedidos leídos a la lista proporcionada
            pedidos.addAll((Collection<? extends Pedido>) pedidosLeidos);
            } catch (StreamReadException ex) {
            throw new RuntimeException(ex);
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        } catch (DatabindException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    }




