package com.example.project.model.exceptions.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAllowedOperationException extends RuntimeException{
}