package com.swagger.swagger.api;

import com.swagger.swagger.domain.Account;
import com.swagger.swagger.domain.User;
import com.swagger.swagger.repository.AccountRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Api(basePath = "/api/account", value = "Account Api", description = "Operations with Account Api",consumes ="application/json", produces = "application/json")
@RestController
@RequestMapping(value = "/api/account",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountApi {
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Account> create(@RequestBody Account account) {


        if (account.getAccountNumber() != null || accountRepository.findByAccountNumber(account.getAccountNumber()) != null) {
            return new ResponseEntity<Account>(HttpStatus.CONFLICT);
        }
        account =accountRepository.save(account);
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> listAll() {
        Iterable<Account> iterable = accountRepository.findAll();
        List<Account> accounts = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> get(@PathVariable("id") long id) {
        Account account = accountRepository.findById(id);
        if (account == null) {
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Account> delete(@PathVariable("id") long id) {
        Account account = accountRepository.findById(id);
        if (account == null) {
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

        accountRepository.delete(id);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }

}
