package com.kernelpanic.apontamentohoras_service.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kernelpanic.apontamentohoras_service.entidades.Hora;
import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;

@Repository
public interface ApontamentoRepositorio extends JpaRepository<Hora, Long> {

    List<Hora> findByUsuarioId(Long usuarioId);

    List<Hora> findByUsuarioIdAndDataLancamentoBetween(
        Long usuarioId,
        LocalDate dataInicio,
        LocalDate dataFim
        );

    List<Hora> findByUsuarioIdAndEstado(Long usuarioId, EstadoHora estado);

    @Query("SELECT h FROM Hora h WHERE " +
           "(:usuarioId IS NULL OR h.usuarioId = :usuarioId) AND " +
           "(:projetoId IS NULL OR h.projetoId = :projetoId) AND " +
           "(:tarefaId IS NULL OR h.tarefaId = :tarefaId) AND " +
           "(:estado IS NULL OR h.estado = :estado) AND " +
           "(:dataInicio IS NULL OR h.dataLancamento >= :dataInicio) AND " +
           "(:dataFim IS NULL OR h.dataLancamento <= :dataFim)")
    List<Hora> buscarComFiltros(
            @Param("usuarioId") Long usuarioId,
            @Param("projetoId") Long projetoId,
            @Param("tarefaId") Long tarefaId,
            @Param("estado") EstadoHora estado,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);

    // Agrega no banco para evitar carregar apontamentos aprovados em memoria no projeto-service.
    @Query(value = """
            SELECT
                h.projeto_id AS projetoId,
                h.usuario_id AS usuarioId,
                CAST(SUM(TIMESTAMPDIFF(MINUTE, h.inicio, h.fim)) / 60.0 AS DECIMAL(12,2)) AS horasAprovadas
            FROM horas h
            WHERE h.estado = 'APROVADO'
              AND h.projeto_id IS NOT NULL
              AND h.data_lancamento BETWEEN :dataInicio AND :dataFim
            GROUP BY h.projeto_id, h.usuario_id
            """, nativeQuery = true)
    List<HorasAprovadasAgregadoProjection> buscarHorasAprovadasAgregadas(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);

    @Query(value = """
            SELECT
                h.projeto_id AS projetoId,
                h.usuario_id AS usuarioId,
                h.data_lancamento AS dataLancamento,
                CAST(SUM(TIMESTAMPDIFF(MINUTE, h.inicio, h.fim)) / 60.0 AS DECIMAL(12,2)) AS horasAprovadas
            FROM horas h
            WHERE h.estado = 'APROVADO'
              AND h.projeto_id IS NOT NULL
              AND h.data_lancamento BETWEEN :dataInicio AND :dataFim
            GROUP BY h.projeto_id, h.usuario_id, h.data_lancamento
            ORDER BY h.data_lancamento
            """, nativeQuery = true)
    List<HorasAprovadasEvolucaoProjection> buscarEvolucaoHorasAprovadas(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);
}
