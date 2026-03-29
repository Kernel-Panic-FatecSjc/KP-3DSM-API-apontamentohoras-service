package com.kernelpanic.apontamentohoras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kernelpanic.apontamentohoras.model.EstadoHora;
import com.kernelpanic.apontamentohoras.model.Hora;

@Repository
public interface HoraRepository extends JpaRepository<Hora, Long> {

    List<Hora> findByUsuarioId(Long usuarioId);

    List<Hora> findByUsuarioIdAndEstado(Long usuarioId, EstadoHora estado);
}