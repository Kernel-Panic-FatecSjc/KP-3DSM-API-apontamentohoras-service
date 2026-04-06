package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;
import com.kernelpanic.apontamentohoras_service.enums.TipoAtividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HorasExibirDTO {

    @NotNull(message = "O ID do apontamento é obrigatório")
    private Long id;

    private Long tarefaId;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "O título da sessão é obrigatório")
    private String tituloSessao;

    @NotNull(message = "O tipo de atividade é obrigatório")
    private TipoAtividade tipoAtividade;

    private String descricao;

    @NotNull(message = "A data de lançamento é obrigatória")
    private LocalDate dataLancamento;

    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime inicio;

    @NotNull(message = "O horário de fim é obrigatório")
    private LocalTime fim;

    private String justificativa;

    private String motivoRejeicao;

    @NotNull(message = "O estado do apontamento é obrigatório")
    private EstadoHora estado;

    @NotNull(message = "A data de criação é obrigatória")
    private LocalDateTime dataCriacao;
}