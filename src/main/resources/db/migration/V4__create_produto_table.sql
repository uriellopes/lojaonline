CREATE TABLE IF NOT EXISTS produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    fabricante_id INT NOT NULL,
    FOREIGN KEY (fabricante_id) REFERENCES fabricante(id)
);