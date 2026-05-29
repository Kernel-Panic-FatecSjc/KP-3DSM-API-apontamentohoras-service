-- Índice para projeto_id — melhora performance dos filtros por projeto na auditoria
-- Também torna alterado_por_id nullable para tolerar requisições sem token JWT
ALTER TABLE auditoria_horas MODIFY COLUMN alterado_por_id BIGINT NULL;
CREATE INDEX IF NOT EXISTS idx_audit_projeto_id ON auditoria_horas (projeto_id);
