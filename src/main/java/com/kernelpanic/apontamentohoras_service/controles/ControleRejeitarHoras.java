package com.kernelpanic.apontamentohoras_service.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasExibirDTO;
import com.kernelpanic.apontamentohoras_service.dtos.HorasRejeitarDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleRejeitarHoras {

    private final ApontamentoServico horaService;

    @PatchMapping("/rejeitar")
    public ResponseEntity<HorasExibirDTO> rejeitar(@Valid @RequestBody HorasRejeitarDTO dto) {
        
        HorasExibirDTO rejeitada = horaService.rejeitarHora(dto.getId(), dto);
        
        return ResponseEntity.ok(rejeitada);
    }
}