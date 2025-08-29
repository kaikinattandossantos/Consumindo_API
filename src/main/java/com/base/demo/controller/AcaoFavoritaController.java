// src/main/java/com/base/demo/controller/AcaoFavoritaController.java
package com.base.demo.controller;

import com.base.demo.model.AcaoFavorita;
import com.base.demo.service.AcaoFavoritaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Anotação que combina @Controller e @ResponseBody, ideal para APIs REST
@RequestMapping("/api/acoes-favoritas") // URL base para todos os endpoints deste controller
public class AcaoFavoritaController {

    @Autowired
    private AcaoFavoritaService service;

    // CREATE -> POST /api/acoes-favoritas
    @PostMapping
    public AcaoFavorita criarAcao(@RequestBody AcaoFavorita acao) {
        return service.salvar(acao);
    }

    // READ (todas) -> GET /api/acoes-favoritas
    @GetMapping
    public List<AcaoFavorita> listarTodasAcoes() {
        return service.listarTodas();
    }

    // READ (por ID) -> GET /api/acoes-favoritas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AcaoFavorita> buscarAcaoPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o objeto
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // UPDATE -> PUT /api/acoes-favoritas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AcaoFavorita> atualizarAcao(@PathVariable Long id, @RequestBody AcaoFavorita acao) {
        try {
            AcaoFavorita atualizada = service.atualizar(id, acao);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE -> DELETE /api/acoes-favoritas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAcao(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content, sucesso sem corpo
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}