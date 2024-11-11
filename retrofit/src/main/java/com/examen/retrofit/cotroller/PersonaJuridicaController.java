package com.examen.retrofit.cotroller;

import com.examen.retrofit.aggregates.response.ResponseSunatRucExt;
import com.examen.retrofit.service.PersonaJuridicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/personaJuridica")
public class PersonaJuridicaController {
    private final PersonaJuridicaService personaJuridicaService;

    public PersonaJuridicaController(PersonaJuridicaService personaJuridicaService) {
        this.personaJuridicaService = personaJuridicaService;
    }


    @GetMapping("/sunat/{ruc}")
    public ResponseEntity<ResponseSunatRucExt> getInfoReniec(@PathVariable String ruc) throws IOException {
        return new ResponseEntity<>(personaJuridicaService.getInfoReniec(ruc), HttpStatus.OK);
    }
}
