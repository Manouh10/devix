-- =========================
-- Table des rôles
-- =========================
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE -- ex: AGENT, MANAGER, ADMIN
);

-- =========================
-- Table des utilisateurs
-- =========================
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- =========================
-- Table des devises
-- =========================
CREATE TABLE currencies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code CHAR(3) NOT NULL UNIQUE, -- ex: USD, EUR, MGA
    name VARCHAR(50) NOT NULL
);

-- =========================
-- Table des statuts dynamiques
-- =========================
CREATE TABLE order_status (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE, -- ex: EN_ATTENTE, VALIDE, ANNULE, A_MODIFIER
    description VARCHAR(255)
);

-- =========================
-- Table des ordres
-- =========================
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('ACHAT', 'VENTE') NOT NULL,
    currency_from_id INT NOT NULL,
    currency_to_id INT NOT NULL,
    amount DECIMAL(20,2) NOT NULL,
    rate DECIMAL(20,6) NOT NULL,
    converted_amount DECIMAL(20,2) GENERATED ALWAYS AS (amount * rate) STORED,
    status_id INT NOT NULL,
    created_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (currency_from_id) REFERENCES currencies(id),
    FOREIGN KEY (currency_to_id) REFERENCES currencies(id),
    FOREIGN KEY (status_id) REFERENCES order_status(id),
    FOREIGN KEY (created_by) REFERENCES users(id)
);

-- =========================
-- Table d'historique des ordres
-- =========================
CREATE TABLE order_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    changed_by INT NOT NULL,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    field_name VARCHAR(50) NOT NULL,
    old_value VARCHAR(255),
    new_value VARCHAR(255),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (changed_by) REFERENCES users(id)
);

-- =========================
-- Table des autorisations
-- =========================
CREATE TABLE authorizations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_id INT NOT NULL,
    entity VARCHAR(50) NOT NULL, -- ex: ORDER
    can_read BOOLEAN DEFAULT FALSE,
    can_write BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- =========================
-- Exemple d’insertion initiale
-- =========================

-- Rôles
INSERT INTO roles (name) VALUES ('AGENT'), ('MANAGER'), ('ADMIN');

-- Statuts des ordres
INSERT INTO order_status (name, description) VALUES
('EN_ATTENTE', 'Ordre créé mais non validé'),
('VALIDE', 'Ordre validé'),
('ANNULE', 'Ordre annulé'),
('A_MODIFIER', 'Ordre à modifier');

-- Devises
INSERT INTO currencies (code, name) VALUES
('USD', 'Dollar américain'),
('EUR', 'Euro'),
('MGA', 'Ariary malgache');

-- Exemple utilisateur
INSERT INTO users (username, password, role_id) VALUES
('agent1', 'password_hash', 1),
('manager1', 'password_hash', 2);

-- Exemple autorisation
INSERT INTO authorizations (role_id, entity, can_read, can_write) VALUES
(1, 'ORDER', TRUE, TRUE),    -- Agent peut lire/écrire ses ordres
(2, 'ORDER', TRUE, TRUE),    -- Manager peut lire/écrire tous les ordres
(3, 'ORDER', TRUE, TRUE);    -- Admin full access
