package com.base.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {

    @JsonProperty("results")
    private List<Acao> resultados;

    // Getters e Setters
    public List<Acao> getResultados() { return resultados; }
    public void setResultados(List<Acao> resultados) { this.resultados = resultados; }
}