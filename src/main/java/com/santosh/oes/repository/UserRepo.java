package com.santosh.oes.repository;

import com.santosh.oes.model.UserOes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserOes,Integer> {
    Optional<UserOes> findByUsername(String username);
}
