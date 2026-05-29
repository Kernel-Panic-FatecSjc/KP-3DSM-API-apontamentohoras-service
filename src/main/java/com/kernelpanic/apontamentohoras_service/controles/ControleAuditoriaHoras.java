package com.kernelpanic.apontamentohoras_service.controles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.AuditoriaHoraRespostaDTO;
import com.kernelpanic.apontamentohoras_service.servicos.AuditoriaHoraServico;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ControleAuditoriaHoras {

    private final AuditoriaHoraServico auditoriaServico;

    /**
     * GET /horas/{id}/auditoria?page=0&size=20
     * Histórico de alterações de um apontamento específico, paginado.
     */
    @GetMapping("/horas/{id}/auditoria")
    public ResponseEntity<Page<AuditoriaHoraRespostaDTO>> buscarAuditoriaPorHora(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(auditoriaServico.buscarPorHora(id, pageable));
    }

    /**
     * GET /auditoria
     * Consulta global com filtros opcionais: usuarioId, projetoId, dataInicio, dataFim.
     *
     * Exemplos:
     *   GET /auditoria?page=0&size=20
     *   GET /auditoria?usuarioId=5
     *   GET /auditoria?projetoId=3
     *   GET /auditoria?dataInicio=2026-05-01&dataFim=2026-05-31
     *   GET /auditoria?usuarioId=5&projetoId=3&page=0&size=10
     */
    @GetMapping("/auditoria")
    public ResponseEntity<Page<AuditoriaHoraRespostaDTO>> buscarAuditoria(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long projetoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        LocalDateTime inicio = dataInicio != null ? dataInicio.atStartOfDay() : null;
        LocalDateTime fim    = dataFim    != null ? dataFim.atTime(LocalTime.MAX) : null;

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(auditoriaServico.buscarComFiltros(usuarioId, projetoId, inicio, fim, pageable));
    }
}


