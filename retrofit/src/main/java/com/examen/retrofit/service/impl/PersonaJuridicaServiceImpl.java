package com.examen.retrofit.service.impl;

import com.examen.retrofit.aggregates.constants.Constants;
import com.examen.retrofit.aggregates.response.ResponseSunatRucExt;
import com.examen.retrofit.entity.PersonaJuridicaEntity;
import com.examen.retrofit.redis.RedisService;
import com.examen.retrofit.repository.PersonaJuridicaRepository;
import com.examen.retrofit.retrofit.ClienteSunatService;
import com.examen.retrofit.retrofit.impl.ClienteSunatServiceImpl;
import com.examen.retrofit.service.PersonaJuridicaService;
import com.examen.retrofit.util.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.Objects;


@Service
public class PersonaJuridicaServiceImpl implements PersonaJuridicaService {
    private final RedisService redisService;

    ClienteSunatService sunatServiceRetrofit = ClienteSunatServiceImpl
            .getRetrofit()
            .create(ClienteSunatService.class);

    @Value("${token.api}")
    private String token;

    public PersonaJuridicaServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public ResponseSunatRucExt getInfoReniec(String ruc) throws IOException {
        ResponseSunatRucExt datosSunat = new ResponseSunatRucExt();
        String redisInfo = redisService.getDataFromRedis(Constants.REDIS_KEY_API_SUNAT + ruc);
        if(Objects.nonNull(redisInfo)){
            datosSunat = Util.convertirDesdeString(redisInfo,ResponseSunatRucExt.class);
            return datosSunat;
        } else {
            datosSunat = getEntity(ruc);
            String dataForRedis = Util.convertirAString(datosSunat);
            redisService.saveInRedis(Constants.REDIS_KEY_API_SUNAT + ruc,dataForRedis,Constants.REDIS_TTL);
            return datosSunat;
        }
    }

    private Call<ResponseSunatRucExt> prepareSunatRetrofit(String ruc){
        String tokenComplete = "Bearer " + token;
        return sunatServiceRetrofit.getInfoSunat(tokenComplete, ruc);

    }

    private ResponseSunatRucExt getEntity(String ruc) throws IOException{
        Call<ResponseSunatRucExt> clienteRetrofit = prepareSunatRetrofit(ruc);
        Response<ResponseSunatRucExt> executeSunat =clienteRetrofit.execute();
        ResponseSunatRucExt datosSunat = null;
        if(executeSunat.isSuccessful() && Objects.nonNull(executeSunat.body())){
            datosSunat = executeSunat.body();
        }
        return datosSunat;
    }
}
