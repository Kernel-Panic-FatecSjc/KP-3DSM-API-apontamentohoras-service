CREATE TABLE IF NOT EXISTS auditoria_horas (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    hora_id       BIGINT       NOT NULL,
    alterado_por_id   BIGINT   NULL,
    usuario_id    BIGINT       NOT NULL,
    projeto_id    BIGINT,
    campo         VARCHAR(100) NOT NULL,
    valor_anterior TEXT,
    valor_novo    TEXT,
    data_alteracao DATETIME    NOT NULL,

    INDEX idx_audit_hora_id      (hora_id),
    INDEX idx_audit_usuario_id   (usuario_id),
    INDEX idx_audit_alterado_por (alterado_por_id),
    INDEX idx_audit_projeto_id   (projeto_id),
    INDEX idx_audit_data         (data_alteracao)
);
