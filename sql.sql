use bancoapontamento;

CREATE TABLE usuarios_ref (
    id INT PRIMARY KEY, -- O ID vem do Kafka, não é auto_increment aqui
    gerente_id INT NULL,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(30),
    
    CONSTRAINT fk_ref_gerente 
        FOREIGN KEY (gerente_id) REFERENCES usuarios_ref(id)
);

CREATE TABLE projetos_ref (
    id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE tarefas_ref (
    id INT PRIMARY KEY,
    projeto_id INT NOT NULL,
    nome_tarefa VARCHAR(100), -- Adicionei o nome para você não ter que ir no outro banco buscar
    aberta BOOLEAN DEFAULT TRUE,
    
    CONSTRAINT fk_ref_projeto 
        FOREIGN KEY (projeto_id) REFERENCES projetos_ref(id)
);

CREATE TABLE horas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tarefa_id INT NOT NULL, -- Obrigatório para saber onde foi gasto
    usuario_id INT NOT NULL, -- Quem lançou
    titulo_sessao VARCHAR(255) NOT NULL,
    tipo_atividade VARCHAR(30) NOT NULL,
    descricao VARCHAR(500),
    data_lancamento DATE NOT NULL, -- Mudei para DATE, pois o horário está em inicio/fim
    inicio TIME NOT NULL,
    fim TIME NOT NULL,
    estado VARCHAR(30) DEFAULT 'PENDENTE',
    justificativa TEXT,
    motivo_rejeicao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_horas_tarefa_ref
        FOREIGN KEY (tarefa_id) REFERENCES tarefas_ref(id),
        
    CONSTRAINT fk_horas_usuario_ref
        FOREIGN KEY (usuario_id) REFERENCES usuarios_ref(id)
);