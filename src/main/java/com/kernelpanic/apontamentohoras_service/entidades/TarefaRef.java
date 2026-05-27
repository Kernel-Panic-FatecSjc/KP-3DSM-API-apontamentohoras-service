package com.kernelpanic.apontamentohoras_service.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarefas_ref")
public class TarefaRef {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "projeto_id")
    private Long projetoId;

    @Column(name = "nome_tarefa")
    private String nomeTarefa;

    @Column(name = "aberta")
    private Boolean aberta;
}
