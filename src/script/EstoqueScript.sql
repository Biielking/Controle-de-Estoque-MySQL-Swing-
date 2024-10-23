create database estoqueDB;

use estoqueDB;
CREATE TABLE estoque (
    idProduto INT PRIMARY KEY AUTO_INCREMENT,
    quantidade INT NOT NULL,
    preco DOUBLE NOT NULL
);

