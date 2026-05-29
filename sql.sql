use bancoapontamento;

CREATE TABLE usuarios_ref (
    id INT PRIMARY KEY,
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

CREATE TABLE horas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tarefa_id INT NULL,
    projeto_id INT NULL,
    usuario_id INT NOT NULL,
    titulo_sessao VARCHAR(255) NOT NULL,
    tipo_atividade VARCHAR(30) NOT NULL,
    descricao VARCHAR(500),
    data_lancamento DATE NOT NULL,
    inicio TIME NOT NULL,
    fim TIME NOT NULL,
    estado VARCHAR(30) DEFAULT 'PENDENTE',
    justificativa TEXT,
    motivo_rejeicao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_horas_usuario_ref
        FOREIGN KEY (usuario_id) REFERENCES usuarios_ref(id)
);
