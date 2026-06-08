INSERT INTO horas (
    tarefa_id,
    projeto_id,
    usuario_id,
    titulo_sessao,
    tipo_atividade,
    descricao,
    data_lancamento,
    inicio,
    fim,
    justificativa,
    motivo_rejeicao,
    estado,
    data_criacao
)
VALUES
(NULL, 1, 3, 'Desenvolvimento API REST', 'DESENVOLVIMENTO',
 'Implementação dos endpoints de autenticação',
 '2026-05-02', '08:00:00', '12:00:00',
 NULL, NULL, 'APROVADO', NOW());