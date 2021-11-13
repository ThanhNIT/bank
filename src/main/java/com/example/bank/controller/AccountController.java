package com.example.bank.controller;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> createAccount(@RequestBody Account account){
        return accountService.save(account);
    }

    @GetMapping("/accounts")
    public Flux<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @DeleteMapping("/accounts/{id}")
    public String deteteAccount(@PathVariable int id){
        accountService.deleteAccount(id);
        return "success";
    }

    @PutMapping("accounts/{id}")
    public Mono<Account> updateAccount(@RequestBody Account account){
        return accountService.update(account);
    }
}
