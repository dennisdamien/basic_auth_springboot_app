package com.tst.basicauth.repository;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.tst.basicauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
