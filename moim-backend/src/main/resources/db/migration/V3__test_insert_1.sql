/*
* File: V3__test_insert_1.sql
* Project: Moim Back-end
* Desc: Insert for testing
* Author: ChulJJA
* Created: 09.07.2025.
* Last Modified: 09.07.2025.
*/

INSERT INTO roles (code, name)
VALUES
    ('L1','Leader'),
    ('L2','Sub Leader'),
    ('M1','Member');

INSERT INTO users (email, password_hash, nickname)
VALUES ('cj.chuleee@gmail.com','{noop}1234','ChulJJA');

INSERT INTO communities (slug, name)
VALUES ('rutgers','Rutgers University');

INSERT INTO boards (community_id, slug, name)
SELECT c.id, 'general', 'General' FROM communities c WHERE c.slug='rutgers';