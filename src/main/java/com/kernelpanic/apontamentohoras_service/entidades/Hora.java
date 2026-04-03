package com.kernelpanic.apontamentohoras_service.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;
import com.kernelpanic.apontamentohoras_service.enums.TipoAtividade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "horas")
public class Hora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tarefa_id")
    private Long tarefaId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "titulo_sessao", nullable = false, length = 255)
    private String tituloSessao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_atividade", nullable = false, length = 30)
    private TipoAtividade tipoAtividade;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "inicio", nullable = false)
    private LocalTime inicio;

    @Column(name = "fim", nullable = false)
    private LocalTime fim;

    @Column(name = "justificativa")
    private String justificativa;

    @Column(name = "motivo_rejeicao")
    private String motivoRejeicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 30)
    private EstadoHora estado;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        if (this.estado == null) {
            if (this.dataLancamento != null && this.dataLancamento.isBefore(LocalDate.now())) {
                this.estado = EstadoHora.AGUARDANDO_APROVACAO;
            } else {
                this.estado = EstadoHora.PENDENTE;
            }
        }
    }
}