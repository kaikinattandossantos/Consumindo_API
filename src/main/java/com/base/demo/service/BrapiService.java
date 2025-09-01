// src/main/java/com/base/demo/service/BrapiService.java
package com.base.demo.service;

import com.base.demo.dto.AcaoDTO;
import com.base.demo.dto.BrapiResponseDTO;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // 1. IMPORTANTE: Importar a anotação @Value
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrapiService {

    // 2. INJETAR O VALOR DA CHAVE DO ARQUIVO DE CONFIGURAÇÃO
    // Esta anotação lê a propriedade 'brapi.api.token' do seu application.properties
    // e armazena o valor dela na variável 'apiToken' abaixo.
    @Value("${brapi.api.token}")
    private String apiToken;

    private static final String API_URL_BASE = "https://brapi.dev/api/quote/";
    
    private final RestTemplate restTemplate;

    @Autowired
    public BrapiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Async
    public CompletableFuture<AcaoDTO> consultarAcaoAsync(String ticker) {
        AcaoDTO acao = consultarAcao(ticker); // chama seu método atual
        return CompletableFuture.completedFuture(acao);
    }

    public AcaoDTO consultarAcao(String ticker) {
        if (ticker == null || ticker.trim().isEmpty()) {
            return null;
        }

        // 3. USAR A CHAVE PARA CONSTRUIR A URL COMPLETA
        // Agora, adicionamos o parâmetro "?token=" com a nossa chave em cada chamada.
        String urlCompleta = API_URL_BASE + ticker + "?token=" + apiToken;
        
        System.out.println("[BrapiService] URL da API sendo chamada: " + urlCompleta);
        
        try {
            BrapiResponseDTO response = restTemplate.getForObject(urlCompleta, BrapiResponseDTO.class);
            
            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                return response.getResults().get(0);
            }
        } catch (Exception e) {
            System.err.println("[BrapiService] Ocorreu um erro ao chamar a API da Brapi:");
            e.printStackTrace();
            return null;
        }
        
        return null;
    }
}