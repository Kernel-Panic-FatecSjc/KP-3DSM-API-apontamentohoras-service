package com.kernelpanic.apontamentohoras_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HorasAprovarDTO {
    @NotNull(message = "O ID do apontamento é obrigatório")
    private Long id;
}