package com.base.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Ignora propriedades do JSON que não mapeamos aqui, para evitar erros.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Acao {

    @JsonProperty("symbol")
    private String codigo;

    @JsonProperty("longName")
    private String nomeLongo;

    @JsonProperty("regularMarketPrice")
    private double preco;

    @JsonProperty("currency")
    private String moeda;

    // Getters e Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNomeLongo() { return nomeLongo; }
    public void setNomeLongo(String nomeLongo) { this.nomeLongo = nomeLongo; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public String getMoeda() { return moeda; }
    public void setMoeda(String moeda) { this.moeda = moeda; }

    @Override
    public String toString() {
        return "Ação {" +
                "Código='" + codigo + '\'' +
                ", Nome='" + nomeLongo + '\'' +
                ", Preço=" + preco +
                ", Moeda='" + moeda + '\'' +
                '}';
    }
}