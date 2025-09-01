// src/main/java/com/base/demo/controller/AcaoFavoritaController.java
package com.base.demo.controller;

import com.base.demo.dto.AcaoFavoritaDetalhadaDTO;
import com.base.demo.model.AcaoFavorita;
import com.base.demo.service.AcaoFavoritaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/acoes-favoritas") 
public class AcaoFavoritaController {

    @Autowired
    private AcaoFavoritaService service;

    @PostMapping
    public AcaoFavorita criarAcao(@RequestBody AcaoFavorita acao) {
        return service.salvar(acao);
    }

    @GetMapping
    public List<AcaoFavorita> listarTodasAcoes() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoFavorita> buscarAcaoPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoFavorita> atualizarAcao(@PathVariable Long id, @RequestBody AcaoFavorita acao) {
        try {
            AcaoFavorita atualizada = service.atualizar(id, acao);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcao(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build(); 
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detalhes")
    public List<AcaoFavoritaDetalhadaDTO> listarFavoritasComDetalhes() {
        return service.listarFavoritasComPreco();
    }
}