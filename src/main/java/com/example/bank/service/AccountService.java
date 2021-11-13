package com.example.bank.service;

import com.example.bank.entity.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Flux<Account> getAllAccounts() {

        Flux.defer(() -> Flux.fromIterable(this.accountRepository.findAll()));

        return Flux.defer(() -> Flux.fromIterable(this.accountRepository.findAll()));
    }

    public Mono<Account> save(Account account) {

        return Mono.just(accountRepository.save(account));
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    public Mono<Account> update(Account account) {

        return accountRepository.existsById(account.getId())?Mono.just(accountRepository.save(account)):null;
    }
}