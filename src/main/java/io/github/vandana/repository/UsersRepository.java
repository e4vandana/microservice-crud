package io.github.vandana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.vandana.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

}

