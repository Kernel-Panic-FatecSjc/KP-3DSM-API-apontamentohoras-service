package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDate;
import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;
import lombok.Data;

@Data
public class HorasFiltroDTO {
    private Long usuarioId;
    private Long projetoId;
    private Long tarefaId;
    private EstadoHora estado;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
