CREATE TABLE TB_CADASTRO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    idade INT,
    -- adicione outras colunas conforme necessário
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);