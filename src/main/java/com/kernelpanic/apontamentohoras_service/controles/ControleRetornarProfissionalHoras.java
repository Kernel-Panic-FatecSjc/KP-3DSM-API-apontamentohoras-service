package com.kernelpanic.apontamentohoras_service.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kernelpanic.apontamentohoras_service.dtos.HorasResumoDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleRetornarProfissionalHoras {

    private final ApontamentoServico horaService;

    @GetMapping("/{id}/resumo")
    public ResponseEntity<HorasResumoDTO> obterResumoProfissional(
            @PathVariable Long id,
            @RequestParam int mes,
            @RequestParam int ano) {

        HorasResumoDTO resultado = horaService.obterResumoPorProfissional(id, mes, ano);
        return ResponseEntity.ok(resultado);
    }
}