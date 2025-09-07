/*
* File: V2__core_entities.sql
* Project: Moim Back-end
* Desc: Basic MVP tables added
* Author: ChulJJA
* Created: 09.07.2025.
* Last Modified: 09.07.2025.
*/

-- USERS & ROLES
CREATE TABLE roles (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       code VARCHAR(32) NOT NULL UNIQUE,     -- LEADER, SUBLEADER, MEMBER
                       name VARCHAR(64) NOT NULL
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       nickname VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- COMMUNITIES (e.g., university)
CREATE TABLE communities (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             slug VARCHAR(64) NOT NULL UNIQUE,   -- e.g., rutgers
                             name VARCHAR(255) NOT NULL
);

-- MEMBERSHIP (user in community with role)
CREATE TABLE memberships (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             user_id BIGINT NOT NULL,
                             community_id BIGINT NOT NULL,
                             role_id BIGINT NOT NULL,
                             joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             UNIQUE KEY uq_member (user_id, community_id),
                             CONSTRAINT fk_mem_user FOREIGN KEY (user_id) REFERENCES users(id),
                             CONSTRAINT fk_mem_comm FOREIGN KEY (community_id) REFERENCES communities(id),
                             CONSTRAINT fk_mem_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- BOARDS (within a community, e.g., “general”, “market”)
CREATE TABLE boards (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        community_id BIGINT NOT NULL,
                        slug VARCHAR(64) NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        UNIQUE KEY uq_board (community_id, slug),
                        CONSTRAINT fk_board_comm FOREIGN KEY (community_id) REFERENCES communities(id)
);

-- POSTS
CREATE TABLE posts (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       board_id BIGINT NOT NULL,
                       author_id BIGINT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NULL,
                       CONSTRAINT fk_post_board FOREIGN KEY (board_id) REFERENCES boards(id),
                       CONSTRAINT fk_post_author FOREIGN KEY (author_id) REFERENCES users(id),
                       INDEX idx_posts_board_created (board_id, created_at)
);

-- COMMENTS
CREATE TABLE comments (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          post_id BIGINT NOT NULL,
                          author_id BIGINT NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES posts(id),
                          CONSTRAINT fk_comment_author FOREIGN KEY (author_id) REFERENCES users(id),
                          INDEX idx_comments_post_created (post_id, created_at)
);

-- REACTIONS (like)
CREATE TABLE reactions (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           post_id BIGINT NOT NULL,
                           user_id BIGINT NOT NULL,
                           type VARCHAR(16) NOT NULL DEFAULT 'LIKE',
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           UNIQUE KEY uq_react (post_id, user_id, type),
                           CONSTRAINT fk_react_post FOREIGN KEY (post_id) REFERENCES posts(id),
                           CONSTRAINT fk_react_user FOREIGN KEY (user_id) REFERENCES users(id)
);
