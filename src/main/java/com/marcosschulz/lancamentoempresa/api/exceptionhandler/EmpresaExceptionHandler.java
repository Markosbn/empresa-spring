package com.marcosschulz.lancamentoempresa.api.exceptionhandler;

import com.marcosschulz.lancamentoempresa.domain.exception.EntitiesException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

    @ExceptionHandler(EntitiesException.class)
    public ResponseEntity<Object>  handleEmpresaException(EntitiesException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        EmpresaTreatment erroApi = new EmpresaTreatment();
        erroApi.setTituloErro(ex.getMessage());
        erroApi.setDataHoraErro(LocalDateTime.now());
        erroApi.setStatus(status.value());

        return handleExceptionInternal(ex, erroApi, new HttpHeaders(), status, request);
    }
}
