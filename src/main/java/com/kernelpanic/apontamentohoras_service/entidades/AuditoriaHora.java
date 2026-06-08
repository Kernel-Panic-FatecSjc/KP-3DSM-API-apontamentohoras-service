package com.kernelpanic.apontamentohoras_service.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "auditoria_horas")
public class AuditoriaHora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_id", nullable = false)
    private Long horaId;

    @Column(name = "alterado_por_id")
    private Long alteradoPorId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "projeto_id")
    private Long projetoId;

    @Column(name = "campo", nullable = false, length = 100)
    private String campo;

    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(name = "valor_novo", columnDefinition = "TEXT")
    private String valorNovo;

    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;
}
