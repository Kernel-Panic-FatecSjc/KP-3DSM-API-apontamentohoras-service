package com.kernelpanic.apontamentohoras_service.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasExibirDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleEnviarParaAprovacao {

    private final ApontamentoServico horaService;

    @PatchMapping("/{id}/enviar")
    public ResponseEntity<HorasExibirDTO> enviar(@PathVariable Long id) {
        HorasExibirDTO enviada = horaService.enviarParaAprovacao(id);
        return ResponseEntity.ok(enviada);
    }
}
