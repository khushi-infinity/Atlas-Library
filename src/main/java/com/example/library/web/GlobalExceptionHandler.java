package com.example.library.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error/data-integrity");
        mav.addObject("path", request.getRequestURI());
        mav.addObject("message",
                "A data integrity rule was violated (for example a duplicate email or ISBN). "
                        + "Please correct the form and try again.");
        return mav;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(IllegalArgumentException ex, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error/not-found");
        mav.addObject("path", request.getRequestURI());
        mav.addObject("message", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoResource(NoResourceFoundException ex) {
        return new ModelAndView("error/not-found")
                .addObject("path", ex.getResourcePath())
                .addObject("message", "Resource not found.");
    }
}
