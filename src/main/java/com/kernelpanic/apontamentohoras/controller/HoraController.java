package com.kernelpanic.apontamentohoras.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kernelpanic.apontamentohoras.dto.HoraRequest;
import com.kernelpanic.apontamentohoras.dto.HoraResponse;
import com.kernelpanic.apontamentohoras.model.EstadoHora;
import com.kernelpanic.apontamentohoras.service.HoraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class HoraController {

    private final HoraService horaService;

    @PostMapping
    public ResponseEntity<HoraResponse> criar(@Valid @RequestBody HoraRequest request) {
        return ResponseEntity.ok(horaService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HoraResponse> editar(@PathVariable Long id, @Valid @RequestBody HoraRequest request) {
        return ResponseEntity.ok(horaService.editar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        horaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<HoraResponse>> listar(
            @RequestParam Long usuarioId,
            @RequestParam(required = false) EstadoHora estado) {

        if (estado != null) {
            return ResponseEntity.ok(horaService.listarPorUsuarioEEstado(usuarioId, estado));
        }
        return ResponseEntity.ok(horaService.listarPorUsuario(usuarioId));
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<HoraResponse> aprovar(@PathVariable Long id) {
        return ResponseEntity.ok(horaService.aprovar(id));
    }



    @PatchMapping("/{id}/rejeitar")
    public ResponseEntity<HoraResponse> rejeitar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(horaService.rejeitar(id, body.get("motivoRejeicao")));
    }
}