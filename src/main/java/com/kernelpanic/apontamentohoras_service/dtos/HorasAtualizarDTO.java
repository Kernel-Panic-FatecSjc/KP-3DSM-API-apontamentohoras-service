package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.kernelpanic.apontamentohoras_service.enums.TipoAtividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HorasAtualizarDTO {

    @NotNull(message = "O ID do apontamento é obrigatório para atualização")
    private Long id;

    private Long tarefaId;

    private Long projetoId;

    @NotBlank(message = "O título da sessão não pode ser vazio")
    @Size(max = 255)
    private String tituloSessao;

    @NotNull(message = "O tipo de atividade é obrigatório")
    private TipoAtividade tipoAtividade;

    @Size(max = 500)
    private String descricao;

    @NotNull(message = "A data de lançamento é obrigatória")
    private LocalDate dataLancamento;

    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime inicio;

    @NotNull(message = "O horário de término é obrigatório")
    private LocalTime fim;

    private String justificativa;
    
    // Nota: Não incluímos 'estado' ou 'motivoRejeicao' aqui, 
    // pois a edição pelo usuário geralmente reseta o estado para PENDENTE 
    // ou segue uma regra de aprovação automática.
}
