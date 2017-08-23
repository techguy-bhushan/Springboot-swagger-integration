package com.swagger.swagger.repository;

import com.swagger.swagger.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByAccountNumber(String accountNumer);
    Account findById(Long id);
}
