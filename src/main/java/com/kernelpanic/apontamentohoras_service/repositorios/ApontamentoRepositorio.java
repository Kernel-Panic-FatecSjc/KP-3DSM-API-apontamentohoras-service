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

    List<Hora> findByUsuarioIdAndEstado(Long usuarioId, EstadoHora estado);

    @Query("SELECT h FROM Hora h WHERE " +
            "(:usuarioId IS NULL OR h.usuarioId = :usuarioId) AND " +
            "(:tarefaId IS NULL OR h.tarefaId = :tarefaId) AND " +
            "(:estado IS NULL OR h.estado = :estado) AND " +
            "(:dataInicio IS NULL OR h.dataLancamento >= :dataInicio) AND " +
            "(:dataFim IS NULL OR h.dataLancamento <= :dataFim)")
    List<Hora> buscarComFiltros(
            Long usuarioId,
            Long tarefaId,
            EstadoHora estado,
            LocalDate dataInicio,
            LocalDate dataFim);

    @Query("SELECT h FROM Hora h WHERE h.usuarioId = :usuarioId " +
            "AND MONTH(h.dataLancamento) = :mes " +
            "AND YEAR(h.dataLancamento) = :ano")
    List<Hora> findByUsuarioIdAndMesAndAno(
            @Param("usuarioId") Long usuarioId,
            @Param("mes") int mes,
            @Param("ano") int ano);
}