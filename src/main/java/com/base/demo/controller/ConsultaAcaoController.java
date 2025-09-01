package com.base.demo.controller;

import com.base.demo.dto.AcaoDTO;
import com.base.demo.service.BrapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acoes")
public class ConsultaAcaoController {

    @Autowired
    private BrapiService brapiService;

    @GetMapping("/consultar/{ticker}") 
    public ResponseEntity<AcaoDTO> consultarAcao(@PathVariable String ticker) {
        AcaoDTO acao = brapiService.consultarAcao(ticker);
        if (acao != null) {
            return ResponseEntity.ok(acao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}