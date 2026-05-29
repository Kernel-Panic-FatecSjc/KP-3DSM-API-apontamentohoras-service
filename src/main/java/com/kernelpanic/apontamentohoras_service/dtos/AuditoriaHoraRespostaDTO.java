package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AuditoriaHoraRespostaDTO {

    private Long id;
    private Long horaId;
    private Long alteradoPorId;
    private String alteradoPorNome;
    private Long usuarioId;
    private String usuarioNome;
    private Long projetoId;
    private String projetoNome;
    private String campo;
    private String valorAnterior;
    private String valorNovo;
    private LocalDateTime dataAlteracao;
}
