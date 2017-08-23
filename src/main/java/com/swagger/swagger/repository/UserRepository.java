package com.swagger.swagger.repository;

import com.swagger.swagger.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findById(Long id);
}
