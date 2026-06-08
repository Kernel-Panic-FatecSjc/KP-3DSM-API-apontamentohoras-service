package com.kernelpanic.apontamentohoras_service.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Utilitário para obter o ID do usuário autenticado a partir do JWT.
 * O subject do JWT é o ID do usuário (Long), colocado como principal pelo JwtFilter.
 */
@Slf4j
@Component
public class UsuarioAutenticado {

    /**
     * Retorna o ID do usuário autenticado extraído do JWT.
     * Retorna null se não houver token válido na requisição.
     */
    public Long obterIdAtual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.debug("[UsuarioAutenticado] auth={} | tipo={} | name={}",
                auth,
                auth != null ? auth.getClass().getSimpleName() : "null",
                auth != null ? auth.getName() : "null");

        // Verifica especificamente se é autenticação JWT (não AnonymousAuthenticationToken)
        if (!(auth instanceof UsernamePasswordAuthenticationToken)) {
            log.debug("[UsuarioAutenticado] Sem autenticação JWT válida (tipo: {})",
                    auth != null ? auth.getClass().getSimpleName() : "null");
            return null;
        }

        try {
            Long userId = Long.parseLong(auth.getName());
            log.debug("[UsuarioAutenticado] userId autenticado: {}", userId);
            return userId;
        } catch (NumberFormatException e) {
            log.warn("[UsuarioAutenticado] Principal não é um Long válido: '{}'", auth.getName());
            return null;
        }
    }
}
