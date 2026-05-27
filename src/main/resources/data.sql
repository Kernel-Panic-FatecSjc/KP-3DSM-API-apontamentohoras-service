INSERT INTO horas (tarefa_id, usuario_id, titulo_sessao, tipo_atividade, descricao, data_lancamento, inicio, fim, justificativa, motivo_rejeicao, estado, data_criacao)
VALUES
  (NULL, 3, 'Desenvolvimento API REST', 'DESENVOLVIMENTO', 'Implementação dos endpoints de autenticação', '2026-05-02', '08:00', '12:00', NULL, NULL, 'APROVADO', CURRENT_TIMESTAMP),
  (NULL, 3, 'Code Review', 'ANALISE', 'Revisão do PR de integração com o banco', '2026-05-02', '13:00', '15:00', NULL, NULL, 'APROVADO', CURRENT_TIMESTAMP),
  (NULL, 3, 'Testes Unitários', 'TESTES', 'Cobertura dos serviços de usuário', '2026-05-05', '09:00', '12:00', NULL, NULL, 'AGUARDANDO_APROVACAO', CURRENT_TIMESTAMP),
  (NULL, 3, 'Análise de Requisitos', 'ANALISE', 'Levantamento das regras de negócio do módulo financeiro', '2026-05-06', '14:00', '17:00', NULL, NULL, 'AGUARDANDO_APROVACAO', CURRENT_TIMESTAMP),
  (NULL, 3, 'Correção de Bug crítico', 'CORRECAO_BUG', 'Fix no cálculo de horas extras', '2026-05-08', '10:00', '11:30', 'Urgência solicitada pelo gestor', 'Horário de fim inconsistente com log', 'REJEITADO', CURRENT_TIMESTAMP),

  (NULL, 4, 'Desenvolvimento Frontend', 'DESENVOLVIMENTO', 'Criação do dashboard de horas', '2026-05-03', '08:00', '12:00', NULL, NULL, 'APROVADO', CURRENT_TIMESTAMP),
  (NULL, 4, 'Nova Feature de Relatório', 'FEATURE', 'Implementação do relatório mensal', '2026-05-03', '13:00', '14:00', NULL, NULL, 'APROVADO', CURRENT_TIMESTAMP),
  (NULL, 4, 'Implementação de Telas', 'DESENVOLVIMENTO', 'Tela de cadastro de apontamentos', '2026-05-07', '09:00', '17:00', NULL, NULL, 'AGUARDANDO_APROVACAO', CURRENT_TIMESTAMP),
  (NULL, 4, 'Testes de Interface', 'TESTES', 'Validação dos fluxos no ambiente de homologação', '2026-05-09', '10:00', '12:00', NULL, 'Casos de teste incompletos', 'REJEITADO', CURRENT_TIMESTAMP),
  (NULL, 4, 'Feature de Exportação', 'FEATURE', 'Exportação de relatórios em PDF', '2026-05-12', '14:00', '16:00', NULL, 'Fora do escopo da sprint', 'REJEITADO', CURRENT_TIMESTAMP);