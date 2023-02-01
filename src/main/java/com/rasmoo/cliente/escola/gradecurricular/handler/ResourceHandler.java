package com.rasmoo.cliente.escola.gradecurricular.handler;

import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handler(MethodArgumentNotValidException exception) {
        Map<String, String> erros = new HashMap<>();

        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        allErrors.forEach(erro -> {
            String campo = ((FieldError)erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        Response<Map<String, String>> response = new Response<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setData(erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<Response<String>> handlerMateriaException(MateriaException exception) {
        Response<String> response = new Response<String>();
        response.setStatusCode(exception.getHttpStatus().value());
        response.setData(exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

}
