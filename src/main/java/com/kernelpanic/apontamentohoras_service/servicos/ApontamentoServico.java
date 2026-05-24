package com.kernelpanic.apontamentohoras_service.servicos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kernelpanic.apontamentohoras_service.dtos.*;
import com.kernelpanic.apontamentohoras_service.entidades.Hora;
import com.kernelpanic.apontamentohoras_service.enums.EstadoHora;
import com.kernelpanic.apontamentohoras_service.repositorios.ApontamentoRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApontamentoServico {

    private final ApontamentoRepositorio repositorio;

    // --- MÉTODOS DE CONSULTA ---

    public List<HorasExibirDTO> obterTodos() {
        return repositorio.findAll().stream()
                .map(this::converterParaExibirDTO)
                .collect(Collectors.toList());
    }

    public HorasExibirDTO obterPorId(Long id) {
        Hora hora = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Apontamento não encontrado com o ID: " + id));
        return converterParaExibirDTO(hora);
    }

    public List<HorasExibirDTO> listarPorUsuario(Long usuarioId) {
        return repositorio.findByUsuarioId(usuarioId).stream()
                .map(this::converterParaExibirDTO)
                .collect(Collectors.toList());
    }

    // --- MÉTODOS DE ESCRITA ---

    @Transactional
    public HorasExibirDTO cadastrarViaDTO(HorasCadastrar dto) {
        validarHorarios(dto.getInicio(), dto.getFim());
        
        Hora hora = new Hora();
        hora.setTarefaId(dto.getTarefaId());
        hora.setProjetoId(dto.getProjetoId());
        hora.setUsuarioId(dto.getUsuarioId());
        hora.setTituloSessao(dto.getTituloSessao());
        hora.setTipoAtividade(dto.getTipoAtividade());
        hora.setDescricao(dto.getDescricao());
        hora.setDataLancamento(dto.getDataLancamento());
        hora.setInicio(dto.getInicio());
        hora.setFim(dto.getFim());
        hora.setJustificativa(dto.getJustificativa());

        Hora salva = repositorio.save(hora);
        return converterParaExibirDTO(salva);
    }

    @Transactional
    public HorasExibirDTO atualizarViaDTO(Long id, HorasAtualizarDTO dto) { 
        Hora hora = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível localizar o apontamento de ID: " + id));

        if (hora.getEstado() != EstadoHora.PENDENTE) {
            throw new RuntimeException("Apenas apontamentos PENDENTES podem ser editados.");
        }

        validarHorarios(dto.getInicio(), dto.getFim());

        hora.setTarefaId(dto.getTarefaId());
        hora.setProjetoId(dto.getProjetoId());
        hora.setTituloSessao(dto.getTituloSessao());
        hora.setTipoAtividade(dto.getTipoAtividade());
        hora.setDescricao(dto.getDescricao());
        hora.setDataLancamento(dto.getDataLancamento());
        hora.setInicio(dto.getInicio());
        hora.setFim(dto.getFim());
        hora.setJustificativa(dto.getJustificativa());

        Hora horaSalva = repositorio.save(hora);
        return converterParaExibirDTO(horaSalva);
    }

    @Transactional
    public void deletarPorId(Long id) {
        Hora hora = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível excluir. ID não encontrado: " + id));

        if (hora.getEstado() != EstadoHora.PENDENTE) {
            throw new RuntimeException("Não é permitido excluir apontamentos que já foram avaliados.");
        }

        repositorio.delete(hora);
    }

    @Transactional(readOnly = true)
    public HorasExibirDTO buscarPorId(Long id) {
        return repositorio.findById(id)
                .map(this::converterParaExibirDTO)
                .orElseThrow(() -> new RuntimeException("Registro " + id + " não encontrado."));
    }

    // --- MÉTODOS DE APROVAÇÃO (GERÊNCIA) ---

    @Transactional
    public HorasExibirDTO aprovarHora(Long id) {
        Hora hora = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Hora não encontrada"));
        
        hora.setEstado(EstadoHora.APROVADO);
        hora.setMotivoRejeicao(null); 
        
        Hora horaSalva = repositorio.save(hora);
        return converterParaExibirDTO(horaSalva); 
    }

    @Transactional
    public HorasExibirDTO rejeitarHora(Long id, HorasRejeitarDTO dto) {
        Hora hora = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Hora não encontrada"));

        hora.setEstado(EstadoHora.REJEITADO);
        hora.setMotivoRejeicao(dto.getMotivoRejeicao());
        
        Hora salva = repositorio.save(hora);
        return converterParaExibirDTO(salva);
    }

    @Transactional(readOnly = true)
    public List<HorasExibirDTO> filtrarHoras(HorasFiltroDTO filtro) {
        // Aqui você usaria o repositório com uma Specification ou Query customizada
        // Se o seu repositório ainda não tem busca dinâmica, você pode implementar assim:
        return repositorio.buscarComFiltros(
                filtro.getUsuarioId(),
                filtro.getProjetoId(),
                filtro.getTarefaId(),
                filtro.getEstado(),
                filtro.getDataInicio(),
                filtro.getDataFim()
        ).stream()
        .map(this::converterParaExibirDTO)
        .collect(Collectors.toList());
    }


    
    @Transactional
    public HorasExibirDTO enviarParaAprovacao(Long id) {
        Hora hora = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Hora nao encontrada"));

        if (hora.getEstado() != EstadoHora.PENDENTE) {
            throw new RuntimeException("Apenas apontamentos PENDENTES podem ser enviados para aprovacao.");
        }

        hora.setEstado(EstadoHora.AGUARDANDO_APROVACAO);

        Hora salva = repositorio.save(hora);
        return converterParaExibirDTO(salva);
    }
    @Transactional(readOnly = true)
    public List<HorasAprovadasAgregadoDTO> buscarHorasAprovadasAgregadas(java.time.LocalDate dataInicio, java.time.LocalDate dataFim) {
        validarPeriodo(dataInicio, dataFim);
        return repositorio.buscarHorasAprovadasAgregadas(dataInicio, dataFim).stream()
                .map(item -> new HorasAprovadasAgregadoDTO(item.getProjetoId(), item.getUsuarioId(), item.getHorasAprovadas()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HorasAprovadasEvolucaoDTO> buscarEvolucaoHorasAprovadas(java.time.LocalDate dataInicio, java.time.LocalDate dataFim) {
        validarPeriodo(dataInicio, dataFim);
        return repositorio.buscarEvolucaoHorasAprovadas(dataInicio, dataFim).stream()
                .map(item -> new HorasAprovadasEvolucaoDTO(item.getProjetoId(), item.getUsuarioId(), item.getDataLancamento(), item.getHorasAprovadas()))
                .collect(Collectors.toList());
    }
    // --- MAPPERS E VALIDA��ES AUXILIARES ---

    private void validarPeriodo(java.time.LocalDate dataInicio, java.time.LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new RuntimeException("dataInicio e dataFim sao obrigatorios.");
        }
        if (dataFim.isBefore(dataInicio)) {
            throw new RuntimeException("dataFim nao pode ser anterior a dataInicio.");
        }
    }

    private void validarHorarios(java.time.LocalTime inicio, java.time.LocalTime fim) {
        if (fim.isBefore(inicio)) {
            throw new RuntimeException("O horário de término não pode ser anterior ao início.");
        }
    }

    private HorasExibirDTO converterParaExibirDTO(Hora hora) {
        HorasExibirDTO dto = new HorasExibirDTO();
        dto.setId(hora.getId());
        dto.setTarefaId(hora.getTarefaId());
        dto.setProjetoId(hora.getProjetoId());
        dto.setUsuarioId(hora.getUsuarioId());
        dto.setTituloSessao(hora.getTituloSessao());
        dto.setTipoAtividade(hora.getTipoAtividade());
        dto.setDescricao(hora.getDescricao());
        dto.setDataLancamento(hora.getDataLancamento());
        dto.setInicio(hora.getInicio());
        dto.setFim(hora.getFim());
        dto.setJustificativa(hora.getJustificativa());
        dto.setMotivoRejeicao(hora.getMotivoRejeicao());
        dto.setEstado(hora.getEstado());
        dto.setDataCriacao(hora.getDataCriacao());
        return dto;
    }
}




