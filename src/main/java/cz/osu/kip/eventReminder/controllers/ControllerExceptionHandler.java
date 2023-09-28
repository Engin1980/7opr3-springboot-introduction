package cz.osu.kip.eventReminder.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<String> noSuchElementExceptionHandler(NoSuchElementException ex, WebRequest request){
    return new ResponseEntity<>("Element not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
  }

}
