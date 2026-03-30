package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.kernelpanic.apontamentohoras_service.entidades.TipoAtividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HoraRequest {

    private Long tarefaId;

    @NotNull
    private Long usuarioId;

    @NotBlank
    private String tituloSessao;

    @NotNull
    private TipoAtividade tipoAtividade;

    private String descricao;

    @NotNull
    private LocalDate dataLancamento;

    @NotNull
    private LocalTime inicio;

    @NotNull
    private LocalTime fim;

    private String justificativa;

    private String motivoRejeicao;
}