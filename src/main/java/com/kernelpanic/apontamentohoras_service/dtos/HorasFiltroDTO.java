package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.LocalDate;
import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class HorasFiltroDTO {
    private Long usuarioId;
    private Long projetoId;
    private Long tarefaId;
    private EstadoHora estado;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFim;
}
