package org.example.bankingsystem.repository;

import org.example.bankingsystem.model.AccountLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLimitRepository extends JpaRepository<AccountLimit, Long> {

}