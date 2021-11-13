package com.example.bank.handler;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {


    @Autowired
    private AccountService accountService;
    static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    public Mono<ServerResponse> getAllAccounts(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountService.getAllAccounts(), Account.class);

    }

    public Mono<ServerResponse> createAccount(ServerRequest serverRequest) {

        Mono<Account> accountSave = serverRequest.bodyToMono(Account.class);

        return accountSave.flatMap(account ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(accountService.save(account), Account.class));

    }

    public Mono<ServerResponse> deleteAccount(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        accountService.deleteAccount(Integer.parseInt(id));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Success", String.class);
    }

    public Mono<ServerResponse> updateAccount(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Account.class).flatMap(account ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(accountService.save(account), Account.class));
    }

}