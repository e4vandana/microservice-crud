package io.github.vandana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.vandana.entity.UserAccounts;

@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccounts, Long> {

	Optional<UserAccounts> findByAccountNumber(Long accountNumber);

}
