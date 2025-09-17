/*
* File: V4__rbac_roles_user_roles.sql
* Project: Moim Back-end
* Desc: Create roles & user_roles, seed baseline roles, and assign USER to all existing users.
* Author: ChulJJA
* Created: 09.11.2025.
* Last Modified: 09.11.2025.
*/

-- 1) USER_ROLES (junction)
CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT NOT NULL,
                                          role_id BIGINT NOT NULL,
                                          PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Helpful indexes (optional but good for lookups)
CREATE INDEX IF NOT EXISTS idx_user_roles_role_id ON user_roles(role_id);
CREATE INDEX IF NOT EXISTS idx_user_roles_user_id ON user_roles(user_id);

-- 3) Seed baseline roles
INSERT INTO roles (code, name) VALUES
                                   ('ADMIN',     'Administrator'),
                                   ('USER',      'Standard User'),
                                   ('LEADER',    'Community Leader'),
                                   ('SUBLEADER', 'Community Sub-Leader')
    ON DUPLICATE KEY UPDATE
                         name = VALUES(name);

-- 4) Ensure every existing user has at least USER role
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.code = 'USER'
         LEFT JOIN user_roles ur ON ur.user_id = u.id AND ur.role_id = r.id
WHERE ur.user_id IS NULL;

-- 5) (Optional) Promote a specific admin by email — uncomment & edit
INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
JOIN roles r ON r.code = 'ADMIN'
WHERE u.email = 'admin@moim.local';
