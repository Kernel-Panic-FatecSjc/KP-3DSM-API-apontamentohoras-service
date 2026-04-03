package com.kernelpanic.apontamentohoras_service.controles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasCadastrar;
import com.kernelpanic.apontamentohoras_service.dtos.HorasExibirDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleEnviarHoras {

    private final ApontamentoServico horaService;

    @PostMapping
    public ResponseEntity<HorasExibirDTO> criar(@Valid @RequestBody HorasCadastrar request) {
        // O Service processa o cadastro e já devolve o DTO de exibição
        HorasExibirDTO novaHora = horaService.cadastrarViaDTO(request);
        
        // Em um POST, o ideal é retornar o status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(novaHora);
    }
}