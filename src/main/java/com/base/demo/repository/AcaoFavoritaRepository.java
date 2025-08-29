// src/main/java/com/base/demo/repository/AcaoFavoritaRepository.java
package com.base.demo.repository;

import com.base.demo.model.AcaoFavorita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Opcional, mas boa prática para indicar que é um componente de acesso a dados
public interface AcaoFavoritaRepository extends JpaRepository<AcaoFavorita, Long> {
    // É isso! Não precisamos escrever mais nada.
    // O Spring Data JPA já nos fornece métodos como:
    // save(), findById(), findAll(), deleteById(), etc.
    // O <AcaoFavorita, Long> significa que este repositório gerencia a entidade AcaoFavorita,
    // cuja chave primária (ID) é do tipo Long.
}