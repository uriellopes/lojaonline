CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_fabricante INT NOT NULL,
    id_loja INT NOT NULL,
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id),
    FOREIGN KEY (id_loja) REFERENCES usuario(id)
);