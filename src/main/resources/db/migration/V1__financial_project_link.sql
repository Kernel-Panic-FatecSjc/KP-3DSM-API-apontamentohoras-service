DROP PROCEDURE IF EXISTS add_horas_projeto_id_if_missing;

CREATE PROCEDURE add_horas_projeto_id_if_missing()
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = DATABASE()
          AND table_name = 'horas'
          AND column_name = 'projeto_id'
    ) THEN
        ALTER TABLE horas ADD COLUMN projeto_id BIGINT NULL;
    END IF;
END;

CALL add_horas_projeto_id_if_missing();

DROP PROCEDURE add_horas_projeto_id_if_missing;

DROP PROCEDURE IF EXISTS add_idx_horas_financeiro_periodo_if_missing;

CREATE PROCEDURE add_idx_horas_financeiro_periodo_if_missing()
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = DATABASE()
          AND table_name = 'horas'
          AND index_name = 'idx_horas_financeiro_periodo'
    ) THEN
        CREATE INDEX idx_horas_financeiro_periodo
            ON horas (estado, data_lancamento, projeto_id, usuario_id);
    END IF;
END;

CALL add_idx_horas_financeiro_periodo_if_missing();

DROP PROCEDURE add_idx_horas_financeiro_periodo_if_missing;
