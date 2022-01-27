package com.marcosschulz.lancamentoempresa.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class EmpresaExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<EmpresaTreatment.Campo> campoList = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            campoList.add(new EmpresaTreatment.Campo(nome, mensagem));
        }
        EmpresaTreatment erroApi = new EmpresaTreatment();
        erroApi.setStatus(status.value());
        erroApi.setDataHoraErro(LocalDateTime.now());
        erroApi.setTituloErro("Um ou mais campos inválidos! Campos:");
        erroApi.setCampos(campoList);

        return handleExceptionInternal(ex, erroApi, headers, status, request );
    }
}
