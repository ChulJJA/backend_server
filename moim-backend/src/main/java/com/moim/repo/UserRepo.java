/*
 * File: UserRepo.java
 * Project: Moim Back-end
 * Desc: Custom queries by method.
 * Author: ChulJJA
 * Created: 09.07.2025.
 * Last Modified: 09.07.2025.
 */

package com.moim.repo;

import com.moim.domain.User;
import java.util.Optional; // Container for an optional (nullable) value
import org.springframework.data.jpa.repository.JpaRepository; // Spring Data JPA base repository interface

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}