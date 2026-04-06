package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.kernelpanic.apontamentohoras_service.enums.TipoAtividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HorasCadastrar {

    private Long tarefaId; // Pode ser nulo se a hora não for vinculada a uma tarefa específica

    @NotNull(message = "O ID do usuário é obrigatório para vincular o lançamento")
    private Long usuarioId;

    @NotBlank(message = "Dê um título para esta sessão de trabalho")
    @Size(max = 255, message = "O título não pode exceder 255 caracteres")
    private String tituloSessao;

    @NotNull(message = "Selecione o tipo de atividade (Desenvolvimento, Reunião, etc)")
    private TipoAtividade tipoAtividade;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "Informe a data em que o trabalho foi realizado")
    private LocalDate dataLancamento;

    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime inicio;

    @NotNull(message = "O horário de término é obrigatório")
    private LocalTime fim;

    private String justificativa; // Geralmente usado para lançamentos retroativos
}