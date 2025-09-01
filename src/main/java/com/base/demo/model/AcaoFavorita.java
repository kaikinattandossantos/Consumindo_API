package com.base.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data; 

@Entity 
@Data   
public class AcaoFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(unique = true, nullable = false)
    private String ticker;

    private String anotacaoPessoal;

    private String usuarioEmail; // E-mail para onde o alerta será enviado
    private Double precoAlvoCompra; // Alerta quando o preço CAI para este valor ou menos
    private Double precoAlvoVenda; 
}