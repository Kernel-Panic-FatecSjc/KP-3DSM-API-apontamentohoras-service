package com.kernelpanic.apontamentohoras_service.repositorios;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface HorasAprovadasEvolucaoProjection {
    Long getProjetoId();
    Long getUsuarioId();
    LocalDate getDataLancamento();
    BigDecimal getHorasAprovadas();
}
