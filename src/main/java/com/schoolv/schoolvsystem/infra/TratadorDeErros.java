package com.schoolv.schoolvsystem.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity tratarError403(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não autorizado!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors().stream().map(DadosErrorValidacao::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record DadosErrorValidacao(String campo,String mensagem){

        public DadosErrorValidacao(FieldError error) {
            this(error.getField(),error.getDefaultMessage());
        }
    }

}