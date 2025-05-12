package com.revisao.ecommerce.controllers;

import com.revisao.ecommerce.services.RelatorioService;
import net.sf.jasperreports.engine.JRException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "relatorios")
public class RelatorioController {


    @Autowired
    RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<String> gerarRelatorio(@RequestParam String caminho) throws JRException {

        relatorioService.gerarRelatorioPDF(caminho);
        return ResponseEntity.ok("Relatório gerado com sucesso" + caminho);
    }
}
