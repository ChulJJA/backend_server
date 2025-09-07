/*
* File: V1__baseline.sql
* Project: Moim Back-end
* Desc: Initial database migration (baseline). V1
* Author: ChulJJA
* Created: 09.01.2025.
* Last Modified: 09.06.2025.
*/

CREATE TABLE users(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    display_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);