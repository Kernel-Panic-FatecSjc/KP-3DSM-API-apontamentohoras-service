package com.kernelpanic.apontamentohoras_service.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HorasAprovadasAgregadoDTO {
    private Long projetoId;
    private Long usuarioId;
    private BigDecimal horasAprovadas;
}
