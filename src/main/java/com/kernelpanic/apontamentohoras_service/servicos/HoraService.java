package com.kernelpanic.apontamentohoras_service.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kernelpanic.apontamentohoras_service.dtos.HoraRequest;
import com.kernelpanic.apontamentohoras_service.dtos.HoraResponse;
import com.kernelpanic.apontamentohoras_service.entidades.EstadoHora;
import com.kernelpanic.apontamentohoras_service.entidades.Hora;
import com.kernelpanic.apontamentohoras_service.repositorios.HoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HoraService {

    private final HoraRepository horaRepository;

    public HoraResponse criar(HoraRequest request) {
        Hora hora = toEntity(request);
        return toResponse(horaRepository.save(hora));
    }

    public HoraResponse editar(Long id, HoraRequest request) {
        Hora hora = horaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hora não encontrada"));

        if (hora.getEstado() != EstadoHora.PENDENTE) {
            throw new RuntimeException("Apenas registros PENDENTE podem ser editados");
        }

        hora.setTarefaId(request.getTarefaId());
        hora.setTituloSessao(request.getTituloSessao());
        hora.setTipoAtividade(request.getTipoAtividade());
        hora.setDescricao(request.getDescricao());
        hora.setDataLancamento(request.getDataLancamento());
        hora.setInicio(request.getInicio());
        hora.setFim(request.getFim());
        hora.setJustificativa(request.getJustificativa());

        return toResponse(horaRepository.save(hora));
    }

    public void excluir(Long id) {
        Hora hora = horaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hora não encontrada"));

        if (hora.getEstado() != EstadoHora.PENDENTE) {
            throw new RuntimeException("Apenas registros PENDENTE podem ser excluídos");
        }

        horaRepository.deleteById(id);
    }

    public List<HoraResponse> listarPorUsuario(Long usuarioId) {
        return horaRepository.findByUsuarioId(usuarioId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<HoraResponse> listarPorUsuarioEEstado(Long usuarioId, EstadoHora estado) {
        return horaRepository.findByUsuarioIdAndEstado(usuarioId, estado)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public HoraResponse aprovar(Long id) {
        Hora hora = horaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hora não encontrada"));

        hora.setEstado(EstadoHora.APROVADO);
        return toResponse(horaRepository.save(hora));
    }

    public HoraResponse rejeitar(Long id, String motivoRejeicao) {
        Hora hora = horaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hora não encontrada"));

        hora.setEstado(EstadoHora.REJEITADO);
        hora.setMotivoRejeicao(motivoRejeicao);
        return toResponse(horaRepository.save(hora));
    }

    private Hora toEntity(HoraRequest request) {
        Hora hora = new Hora();
        hora.setTarefaId(request.getTarefaId());
        hora.setUsuarioId(request.getUsuarioId());
        hora.setTituloSessao(request.getTituloSessao());
        hora.setTipoAtividade(request.getTipoAtividade());
        hora.setDescricao(request.getDescricao());
        hora.setDataLancamento(request.getDataLancamento());
        hora.setInicio(request.getInicio());
        hora.setFim(request.getFim());
        hora.setJustificativa(request.getJustificativa());
        return hora;
    }

    private HoraResponse toResponse(Hora hora) {
        HoraResponse response = new HoraResponse();
        response.setId(hora.getId());
        response.setTarefaId(hora.getTarefaId());
        response.setUsuarioId(hora.getUsuarioId());
        response.setTituloSessao(hora.getTituloSessao());
        response.setTipoAtividade(hora.getTipoAtividade());
        response.setDescricao(hora.getDescricao());
        response.setDataLancamento(hora.getDataLancamento());
        response.setInicio(hora.getInicio());
        response.setFim(hora.getFim());
        response.setJustificativa(hora.getJustificativa());
        response.setMotivoRejeicao(hora.getMotivoRejeicao());
        response.setEstado(hora.getEstado());
        response.setDataCriacao(hora.getDataCriacao());
        return response;
    }
}