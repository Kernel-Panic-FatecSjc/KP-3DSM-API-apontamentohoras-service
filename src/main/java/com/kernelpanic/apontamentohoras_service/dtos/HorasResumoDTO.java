package com.kernelpanic.apontamentohoras_service.dtos;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;
import com.kernelpanic.apontamentohoras_service.enums.TipoAtividade;

import lombok.Data;

@Data
public class HorasResumoDTO {
    private Long usuarioId;
    private int mes;
    private int ano;
    private Duration totalHorasMes;
    private Map<EstadoHora, Duration> horasPorStatus;
    private Map<TipoAtividade, Duration> horasPorAtividade;
    private List<HorasExibirDTO> lancamentosRejeitados;
}