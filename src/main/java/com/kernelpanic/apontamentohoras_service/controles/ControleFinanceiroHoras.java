package com.kernelpanic.apontamentohoras_service.controles;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasAprovadasAgregadoDTO;
import com.kernelpanic.apontamentohoras_service.dtos.HorasAprovadasEvolucaoDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas/financeiro")
@RequiredArgsConstructor
public class ControleFinanceiroHoras {

    private final ApontamentoServico servico;

    @GetMapping("/aprovadas")
    public List<HorasAprovadasAgregadoDTO> buscarHorasAprovadasAgregadas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return servico.buscarHorasAprovadasAgregadas(dataInicio, dataFim);
    }

    @GetMapping("/aprovadas/evolucao")
    public List<HorasAprovadasEvolucaoDTO> buscarEvolucaoHorasAprovadas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return servico.buscarEvolucaoHorasAprovadas(dataInicio, dataFim);
    }
}
