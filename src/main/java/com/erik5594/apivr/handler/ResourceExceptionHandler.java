package com.erik5594.apivr.handler;

import com.erik5594.apivr.domain.DetalheErro;
import com.erik5594.apivr.exceptions.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author erik_
 * Data Criacao: 19/07/2021 - 21:40
 */

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<DetalheErro> handleValidacaoException
            (ValidacaoException e, HttpServletRequest request) {

        DetalheErro erro = new DetalheErro(e.getMessage(), e.getStatus());

        return ResponseEntity.status(e.getStatus()).body(erro);
    }

}
