// src/main/java/com/base/demo/model/AcaoFavorita.java
package com.base.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data; // Do Lombok, para gerar getters, setters, etc.

@Entity // Anotação que diz ao JPA que esta classe representa uma tabela
@Data   // Anotação do Lombok para evitar escrever código boilerplate (getters, setters, toString, etc.)
public class AcaoFavorita {

    @Id // Marca o campo como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para gerar o valor do ID automaticamente
    private Long id;

    @Column(unique = true, nullable = false) // O ticker deve ser único e não pode ser nulo
    private String ticker;

    private String anotacaoPessoal;
}