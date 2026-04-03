package com.kernelpanic.apontamentohoras_service.controles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras_service.dtos.HorasExibirDTO;
import com.kernelpanic.apontamentohoras_service.servicos.ApontamentoServico;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleBuscarRegistroID {

    private final ApontamentoServico horaService;

    @GetMapping("/{id}")
    public ResponseEntity<HorasExibirDTO> buscarPorId(@PathVariable Long id) {
        HorasExibirDTO resultado = horaService.buscarPorId(id);
        
        return ResponseEntity.ok(resultado);
    }
}