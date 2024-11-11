package com.examen.retrofit.client;

import com.examen.retrofit.aggregates.response.ResponseSunatRucExt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface ClientSunatRucExt {
    @GetMapping("/ruc")
    ResponseSunatRucExt getSunatRucExt(@RequestParam("numero") String numero,
                                       @RequestHeader("Authorization") String authorization);
}
