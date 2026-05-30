package com.kernelpanic.apontamentohoras_service.controles;

import java.time.LocalDate;

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
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim 
    ){

        HorasResumoDTO resultado = horaService.obterResumoPorProfissional(id, dataInicio, dataFim);
        return ResponseEntity.ok(resultado);
    }
}