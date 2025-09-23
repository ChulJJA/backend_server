/*
* File: V5__current_community.sql
* Project: Moim Back-end
* Desc: Add current community.
* Author: ChulJJA
* Created: 09.22.2025.
* Last Modified: 09.22.2025.
*/

-- USERS.current_community_id (nullable)
ALTER TABLE users
    ADD COLUMN IF NOT EXISTS current_community_id BIGINT NULL,
    ADD CONSTRAINT fk_users_current_comm FOREIGN KEY (current_community_id)
    REFERENCES communities(id) ON DELETE SET NULL;
