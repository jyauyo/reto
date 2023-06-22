package com.coralogix.calculator.controller;

import com.coralogix.calculator.proxy.Dato;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/api")
public class CalculadoraController {

    @Value("${urlBase}")
    private String urlBase;

    @GetMapping(value = "/informacion/{monedaOrigen}/{monedaDestino}")
    public Mono<Dato> getInformacion(@PathVariable("monedaOrigen") String monedaOrigen,
                                     @PathVariable("monedaDestino") String monedaDestino){

        //String urlBase = "https://v6.exchangerate-api.com/v6/1227cf5d13731ad6c251bc00";
        WebClient webClient = WebClient.builder().baseUrl(urlBase).build();

        return webClient.get().uri(uriBuilder -> uriBuilder.path("/pair/{monedaOrigen}/{monedaDestino}").build(monedaOrigen, monedaDestino)).retrieve().bodyToMono(Dato.class);

    }
}
