// src/main/java/com/base/demo/InitData.java
package com.base.demo;

import com.base.demo.model.AcaoFavorita;
import com.base.demo.repository.AcaoFavoritaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final AcaoFavoritaRepository repository;

    public InitData(AcaoFavoritaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        AcaoFavorita a1 = new AcaoFavorita();
        a1.setTicker("AAPL");
        a1.setAnotacaoPessoal("Apple");

        AcaoFavorita a2 = new AcaoFavorita();
        a2.setTicker("TSLA");
        a2.setAnotacaoPessoal("Tesla");

        AcaoFavorita a3 = new AcaoFavorita();
        a3.setTicker("AMZN");
        a3.setAnotacaoPessoal("Amazon");

        repository.save(a1);
        repository.save(a2);
        repository.save(a3);

        System.out.println("Ações favoritas inicializadas!");
    }
}
