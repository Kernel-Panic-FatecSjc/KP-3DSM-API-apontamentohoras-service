package com.kernelpanic.apontamentohoras_service.controles;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasExibirDTO;
import com.kernelpanic.apontamentohoras_service.dtos.HorasFiltroDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleFiltrarHoras {

    private final ApontamentoServico horaService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<HorasExibirDTO>> filtrar(HorasFiltroDTO filtro) {
        List<HorasExibirDTO> resultados = horaService.filtrarHoras(filtro);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/profissional/{usuarioId}")
    public ResponseEntity<List<HorasExibirDTO>> obterHorasPorProfissional(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) String dataInicio,
            @RequestParam(required = false) String dataFim) {

        List<HorasExibirDTO> resultados = horaService.obterHorasPorProfissional(usuarioId, dataInicio, dataFim);
        return ResponseEntity.ok(resultados);
    }
} 