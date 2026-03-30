package com.kernelpanic.apontamentohoras_service.controles;

package com.kernelpanic.apontamentohoras_service.controles;

package com.kernelpanic.apontamentohoras_service.controles;

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

import com.kernelpanic.apontamentohoras_service.dtos.HoraRequest;
import com.kernelpanic.apontamentohoras_service.dtos.HoraResponse;
import com.kernelpanic.apontamentohoras_service.entidades.EstadoHora;
import com.kernelpanic.apontamentohoras_service.servicos.HoraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/horas")
@RequiredArgsConstructor
public class ControleEnviarHoras {
    @PostMapping
    public ResponseEntity<HoraResponse> criar(@Valid @RequestBody HoraRequest request) {
        return ResponseEntity.ok(horaService.criar(request));
    }
}
