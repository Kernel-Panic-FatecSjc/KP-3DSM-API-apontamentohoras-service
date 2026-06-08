package com.kernelpanic.apontamentohoras_service.repositorios;

import java.math.BigDecimal;

public interface HorasAprovadasAgregadoProjection {
    Long getProjetoId();
    Long getUsuarioId();
    BigDecimal getHorasAprovadas();
}
