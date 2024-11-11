package com.examen.retrofit.service;

import com.examen.retrofit.aggregates.response.ResponseSunatRucExt;

import java.io.IOException;

public interface PersonaJuridicaService  {
    ResponseSunatRucExt getInfoReniec(String ruc) throws IOException;
}
