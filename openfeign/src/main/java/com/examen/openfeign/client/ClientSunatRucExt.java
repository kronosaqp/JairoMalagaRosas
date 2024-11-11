package com.examen.openfeign.client;

import com.examen.openfeign.aggregates.response.ResponseSunatRucExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-sunat",url = "https://api.apis.net.pe/v2/sunat/")
public interface ClientSunatRucExt {
    @GetMapping("/ruc")
    ResponseSunatRucExt getSunatRucExt(@RequestParam("numero") String numero,
                                       @RequestHeader("Authorization") String authorization);
}
