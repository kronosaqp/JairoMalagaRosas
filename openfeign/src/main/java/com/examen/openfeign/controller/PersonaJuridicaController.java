package com.examen.openfeign.controller;

import com.examen.openfeign.aggregates.response.ResponseSunatRucExt;
import com.examen.openfeign.entity.PersonaJuridicaEntity;
import com.examen.openfeign.service.PersonaJuridicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/personaJuridica")
public class PersonaJuridicaController {
    private final PersonaJuridicaService personaJuridicaService;

    public PersonaJuridicaController(PersonaJuridicaService personaJuridicaService) {
        this.personaJuridicaService = personaJuridicaService;
    }


    @GetMapping("/sunat/{ruc}")
    public ResponseEntity<ResponseSunatRucExt> getInfoReniec(@PathVariable String ruc){
        return new ResponseEntity<>(personaJuridicaService.getInfoReniec(ruc),HttpStatus.OK);
    }
}
