package com.kernelpanic.apontamentohoras_service.controles;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasResumoDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControlePorcentagemHoras {

    private final ApontamentoServico apontamentoServico;

    @GetMapping("/resumo/{usuarioId}")
    public ResponseEntity<HorasResumoDTO> obterResumo(
            @PathVariable Long usuarioId,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ) {

        HorasResumoDTO resumo =
                apontamentoServico.obterResumoPorProfissional(
                        usuarioId,
                        dataInicio,
                        dataFim
                );

        return ResponseEntity.ok(resumo);
    }
}