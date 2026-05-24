package com.kernelpanic.apontamentohoras_service.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HorasAprovadasEvolucaoDTO {
    private Long projetoId;
    private Long usuarioId;
    private LocalDate dataLancamento;
    private BigDecimal horasAprovadas;
}
