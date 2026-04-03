package com.kernelpanic.apontamentohoras_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HorasRejeitarDTO {
    
    @NotNull(message = "O ID do apontamento é obrigatório")
    private Long id;

    @NotBlank(message = "É obrigatório informar o motivo da rejeição")
    @Size(max = 500, message = "O motivo não pode exceder 500 caracteres")
    private String motivoRejeicao;
}