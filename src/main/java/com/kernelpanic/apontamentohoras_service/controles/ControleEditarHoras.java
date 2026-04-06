package com.kernelpanic.apontamentohoras_service.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasAtualizarDTO;
import com.kernelpanic.apontamentohoras_service.dtos.HorasExibirDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleEditarHoras {

    private final ApontamentoServico horaService;

    @PutMapping("/editar") 
    public ResponseEntity<HorasExibirDTO> editar(@Valid @RequestBody HorasAtualizarDTO dto) {
        HorasExibirDTO atualizada = horaService.atualizarViaDTO(dto.getId(), dto);
        
        return ResponseEntity.ok(atualizada);
    }
}