package com.examen.openfeign.service;

import com.examen.openfeign.aggregates.response.ResponseSunatRucExt;

public interface PersonaJuridicaService {
    ResponseSunatRucExt getInfoReniec(String ruc);
}
