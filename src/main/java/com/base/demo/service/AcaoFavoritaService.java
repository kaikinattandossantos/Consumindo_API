// src/main/java/com/base/demo/service/AcaoFavoritaService.java
package com.base.demo.service;

import com.base.demo.model.AcaoFavorita;
import com.base.demo.repository.AcaoFavoritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Anotação que marca a classe como um serviço de negócio
public class AcaoFavoritaService {

    @Autowired // Injeção de dependência: O Spring vai nos dar uma instância do Repository
    private AcaoFavoritaRepository repository;

    // CREATE
    public AcaoFavorita salvar(AcaoFavorita acao) {
        // Poderíamos adicionar regras aqui, como validar se o ticker já existe
        return repository.save(acao);
    }

    // READ (todas)
    public List<AcaoFavorita> listarTodas() {
        return repository.findAll();
    }

    // READ (por ID)
    public Optional<AcaoFavorita> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public AcaoFavorita atualizar(Long id, AcaoFavorita acaoAtualizada) {
        return repository.findById(id)
                .map(acaoExistente -> {
                    acaoExistente.setTicker(acaoAtualizada.getTicker());
                    acaoExistente.setAnotacaoPessoal(acaoAtualizada.getAnotacaoPessoal());
                    return repository.save(acaoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Ação com id " + id + " não encontrada!"));
    }

    // DELETE
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Ação com id " + id + " não encontrada para exclusão!");
        }
        repository.deleteById(id);
    }
}