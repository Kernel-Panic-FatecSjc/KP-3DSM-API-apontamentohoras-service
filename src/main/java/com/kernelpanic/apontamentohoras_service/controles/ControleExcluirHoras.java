package com.kernelpanic.apontamentohoras_service.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleExcluirHoras {

    private final ApontamentoServico horaService; 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        horaService.deletarPorId(id); 
        
        return ResponseEntity.noContent().build(); // Status 204
    }
}