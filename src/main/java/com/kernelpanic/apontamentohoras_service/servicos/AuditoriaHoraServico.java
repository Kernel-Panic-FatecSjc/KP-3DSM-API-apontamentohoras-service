package com.kernelpanic.apontamentohoras_service.servicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.kernelpanic.apontamentohoras_service.config.UsuarioAutenticado;
import com.kernelpanic.apontamentohoras_service.dtos.AuditoriaHoraRespostaDTO;
import com.kernelpanic.apontamentohoras_service.entidades.AuditoriaHora;
import com.kernelpanic.apontamentohoras_service.entidades.Hora;
import com.kernelpanic.apontamentohoras_service.repositorios.AuditoriaHoraRepositorio;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditoriaHoraServico {

    private final AuditoriaHoraRepositorio repositorio;
    private final RestTemplate restTemplate;
    private final UsuarioAutenticado usuarioAutenticado;

    @Value("${integracoes.usuario.url:http://localhost:8083}")
    private String usuarioUrl;

    @Value("${integracoes.projeto.url:http://localhost:8082}")
    private String projetoUrl;

    // -----------------------------------------------------------------------
    // ESCRITA: compara campos e persiste registros de auditoria
    // O usuário que realizou a alteração é obtido do JWT — não vem do frontend.
    // -----------------------------------------------------------------------

    @Transactional
    public void registrarAlteracoes(Hora antes, Hora depois) {
        Long alteradoPorId = usuarioAutenticado.obterIdAtual();

        if (alteradoPorId == null) {
            log.warn("Auditoria ignorada para hora {}: nenhum usuário autenticado na requisição.", antes.getId());
            return;
        }

        List<AuditoriaHora> registros = new ArrayList<>();

        compararCampo(registros, antes, depois, alteradoPorId, "tituloSessao",
                antes.getTituloSessao(), depois.getTituloSessao());
        compararCampo(registros, antes, depois, alteradoPorId, "tipoAtividade",
                strOuNull(antes.getTipoAtividade()), strOuNull(depois.getTipoAtividade()));
        compararCampo(registros, antes, depois, alteradoPorId, "descricao",
                antes.getDescricao(), depois.getDescricao());
        compararCampo(registros, antes, depois, alteradoPorId, "dataLancamento",
                strOuNull(antes.getDataLancamento()), strOuNull(depois.getDataLancamento()));
        compararCampo(registros, antes, depois, alteradoPorId, "inicio",
                strOuNull(antes.getInicio()), strOuNull(depois.getInicio()));
        compararCampo(registros, antes, depois, alteradoPorId, "fim",
                strOuNull(antes.getFim()), strOuNull(depois.getFim()));
        compararCampo(registros, antes, depois, alteradoPorId, "justificativa",
                antes.getJustificativa(), depois.getJustificativa());
        compararCampo(registros, antes, depois, alteradoPorId, "projetoId",
                strOuNull(antes.getProjetoId()), strOuNull(depois.getProjetoId()));
        compararCampo(registros, antes, depois, alteradoPorId, "tarefaId",
                strOuNull(antes.getTarefaId()), strOuNull(depois.getTarefaId()));
        compararCampo(registros, antes, depois, alteradoPorId, "estado",
                strOuNull(antes.getEstado()), strOuNull(depois.getEstado()));
        compararCampo(registros, antes, depois, alteradoPorId, "motivoRejeicao",
                antes.getMotivoRejeicao(), depois.getMotivoRejeicao());

        if (!registros.isEmpty()) {
            repositorio.saveAll(registros);
        }
    }

    // -----------------------------------------------------------------------
    // LEITURA: retorna auditoria paginada, enriquecida com nomes
    // -----------------------------------------------------------------------

    @Transactional(readOnly = true)
    public Page<AuditoriaHoraRespostaDTO> buscarPorHora(Long horaId, Pageable pageable) {
        return repositorio.findByHoraIdOrderByDataAlteracaoDesc(horaId, pageable)
                .map(this::enriquecer);
    }

    @Transactional(readOnly = true)
    public Page<AuditoriaHoraRespostaDTO> buscarComFiltros(Long usuarioId, Long projetoId,
                                                           LocalDateTime dataInicio, LocalDateTime dataFim,
                                                           Pageable pageable) {
        return repositorio.buscarComFiltros(usuarioId, projetoId, dataInicio, dataFim, pageable)
                .map(this::enriquecer);
    }

    // -----------------------------------------------------------------------
    // PRIVADOS
    // -----------------------------------------------------------------------

    private void compararCampo(List<AuditoriaHora> lista, Hora antes, Hora depois,
                                Long alteradoPorId,
                                String campo, String valorAntes, String valorDepois) {
        if (!Objects.equals(valorAntes, valorDepois)) {
            AuditoriaHora registro = new AuditoriaHora();
            registro.setHoraId(antes.getId());
            registro.setAlteradoPorId(alteradoPorId);
            registro.setUsuarioId(antes.getUsuarioId());
            registro.setProjetoId(antes.getProjetoId());
            registro.setCampo(campo);
            registro.setValorAnterior(valorAntes);
            registro.setValorNovo(valorDepois);
            registro.setDataAlteracao(LocalDateTime.now());
            lista.add(registro);
        }
    }

    private AuditoriaHoraRespostaDTO enriquecer(AuditoriaHora auditoria) {
        AuditoriaHoraRespostaDTO dto = new AuditoriaHoraRespostaDTO();
        dto.setId(auditoria.getId());
        dto.setHoraId(auditoria.getHoraId());
        dto.setAlteradoPorId(auditoria.getAlteradoPorId());
        dto.setUsuarioId(auditoria.getUsuarioId());
        dto.setProjetoId(auditoria.getProjetoId());
        dto.setCampo(auditoria.getCampo());
        dto.setValorAnterior(auditoria.getValorAnterior());
        dto.setValorNovo(auditoria.getValorNovo());
        dto.setDataAlteracao(auditoria.getDataAlteracao());
        // Nomes resolvidos em tempo de leitura via HTTP — nome não está no JWT
        dto.setAlteradoPorNome(resolverNomeUsuario(auditoria.getAlteradoPorId()));
        dto.setUsuarioNome(resolverNomeUsuario(auditoria.getUsuarioId()));
        dto.setProjetoNome(resolverNomeProjeto(auditoria.getProjetoId()));
        return dto;
    }

    private String resolverNomeUsuario(Long usuarioId) {
        if (usuarioId == null) return null;
        try {
            NomeRespostaDTO resp = restTemplate.getForObject(
                    usuarioUrl + "/usuario/" + usuarioId, NomeRespostaDTO.class);
            return resp != null ? resp.getNome() : null;
        } catch (Exception e) {
            log.warn("Não foi possível resolver nome do usuário {}: {}", usuarioId, e.getMessage());
            return null;
        }
    }

    private String resolverNomeProjeto(Long projetoId) {
        if (projetoId == null) return null;
        try {
            NomeRespostaDTO resp = restTemplate.getForObject(
                    projetoUrl + "/projeto/" + projetoId, NomeRespostaDTO.class);
            return resp != null ? resp.getNome() : null;
        } catch (Exception e) {
            log.warn("Não foi possível resolver nome do projeto {}: {}", projetoId, e.getMessage());
            return null;
        }
    }

    private String strOuNull(Object valor) {
        return valor != null ? valor.toString() : null;
    }

    // DTO mínimo para deserializar apenas o campo "nome" das respostas dos outros serviços
    @lombok.Data
    public static class NomeRespostaDTO {
        private String nome;
    }
}

