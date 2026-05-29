package com.kernelpanic.apontamentohoras_service.repositorios;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kernelpanic.apontamentohoras_service.entidades.AuditoriaHora;

@Repository
public interface AuditoriaHoraRepositorio extends JpaRepository<AuditoriaHora, Long> {

    Page<AuditoriaHora> findByHoraIdOrderByDataAlteracaoDesc(Long horaId, Pageable pageable);

    @Query("SELECT a FROM AuditoriaHora a WHERE " +
           "(:usuarioId IS NULL OR a.usuarioId = :usuarioId) AND " +
           "(:projetoId IS NULL OR a.projetoId = :projetoId) AND " +
           "(:dataInicio IS NULL OR a.dataAlteracao >= :dataInicio) AND " +
           "(:dataFim IS NULL OR a.dataAlteracao <= :dataFim) " +
           "ORDER BY a.dataAlteracao DESC")
    Page<AuditoriaHora> buscarComFiltros(
            @Param("usuarioId") Long usuarioId,
            @Param("projetoId") Long projetoId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            Pageable pageable);
}
