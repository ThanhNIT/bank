package com.example.bank.router;

import com.example.bank.handler.AccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class AccountRouter {

    @Bean
    public RouterFunction<ServerResponse> productsRoute(AccountHandler accountHandler){

        return RouterFunctions
                .route(GET("/accounts").and(accept(MediaType.APPLICATION_JSON))
                        ,accountHandler::getAllAccounts)
                .andRoute(POST("/accounts").and(accept(MediaType.APPLICATION_JSON))
                        ,accountHandler::createAccount)
                .andRoute(DELETE("/account/{id}").and(accept(MediaType.APPLICATION_JSON))
                        ,accountHandler::deleteAccount)
                .andRoute(PUT("/account/{id}").and(accept(MediaType.APPLICATION_JSON))
                        ,accountHandler::updateAccount);
    }
}
