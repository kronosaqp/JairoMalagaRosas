package com.examen.openfeign.service.impl;

import com.examen.openfeign.aggregates.constants.Constants;
import com.examen.openfeign.aggregates.response.ResponseSunatRucExt;
import com.examen.openfeign.client.ClientSunatRucExt;
import com.examen.openfeign.entity.PersonaJuridicaEntity;
import com.examen.openfeign.redis.RedisService;
import com.examen.openfeign.service.PersonaJuridicaService;
import com.examen.openfeign.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PersonaJuridicaServiceImpl implements PersonaJuridicaService {
    private final RedisService redisService;
    private final ClientSunatRucExt clientSunatRucExt;

    public PersonaJuridicaServiceImpl(RedisService redisService, ClientSunatRucExt clientSunatRucExt) {
        this.redisService = redisService;
        this.clientSunatRucExt = clientSunatRucExt;
    }

    @Value("${token.api}")
    private String token;

    @Override
    public ResponseSunatRucExt getInfoReniec(String ruc) {
        PersonaJuridicaEntity personaJuridicaEntity = new PersonaJuridicaEntity();
        ResponseSunatRucExt datosSunat = new ResponseSunatRucExt();
        String redisInfo = redisService.getDataFromRedis(Constants.REDIS_KEY_API_SUNAT + ruc);
        if(Objects.nonNull(redisInfo)){
            datosSunat = Util.convertirDesdeString(redisInfo,ResponseSunatRucExt.class);
            return datosSunat;
        } else {
            //Aqui consultamos Sunat con OpenFeign
            datosSunat = executionSunatRucExt(ruc);
            String dataForRedis = Util.convertirAString(datosSunat);
            redisService.saveInRedis(Constants.REDIS_KEY_API_SUNAT + ruc,dataForRedis,Constants.REDIS_TTL);
            return datosSunat;
        }
    }

    private ResponseSunatRucExt executionSunatRucExt(String ruc){
        String tokenOk = Constants.BEARER + token;
        return clientSunatRucExt.getSunatRucExt(ruc, tokenOk);
    }
}
