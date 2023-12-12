CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    perfil_id INT NOT NULL,
    role VARCHAR(255) NOT NULL,
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);