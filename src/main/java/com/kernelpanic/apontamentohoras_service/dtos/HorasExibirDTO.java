package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;
import com.kernelpanic.apontamentohoras_service.enums.TipoAtividade;

import lombok.Data;

@Data
public class HorasExibirDTO {
    private Long id;
    private Long tarefaId;
    private Long usuarioId;
    private String tituloSessao;
    private TipoAtividade tipoAtividade;
    private String descricao;
    private LocalDate dataLancamento;
    private LocalTime inicio;
    private LocalTime fim;
    private String justificativa;
    private String motivoRejeicao;
    private EstadoHora estado;
    private LocalDateTime dataCriacao;
    private Double horasTrabalhadas;
}