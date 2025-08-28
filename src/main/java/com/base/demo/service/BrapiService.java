package com.base.demo.service;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.base.demo.model.Acao;
import com.base.demo.model.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;



public class BrapiService {

    private static final String API_URL = "https://brapi.dev/api/quote/";

    public Acao consultarAcao(String ticker) {
        // Valida para garantir que o ticker não está vazio
        if (ticker == null || ticker.trim().isEmpty()) {
            System.out.println("Ticker inválido.");
            return null;
        }

        try {
            // 1. Cria o Cliente HTTP e a Requisição
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + ticker))
                    .build();

            // 2. Envia a requisição e obtém a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 3. Verifica se a requisição foi bem-sucedida (código 200)
            if (response.statusCode() != 200) {
                System.out.println("Erro ao consultar a API: " + response.body());
                return null;
            }

            // 4. Converte o JSON da resposta em objetos Java
            ObjectMapper mapper = new ObjectMapper();
            ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);
            
            // 5. Retorna o primeiro (e único) resultado da lista
            if (apiResponse != null && apiResponse.getResultados() != null && !apiResponse.getResultados().isEmpty()) {
                return apiResponse.getResultados().get(0);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Ocorreu um erro na chamada da API: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
}