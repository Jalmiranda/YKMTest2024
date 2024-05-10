package com.example.ykm.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.http.HttpStatus;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ShipExceptionController {
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoResourceFound() {
		log.error("La llamada utilizada no esta habilitada");
	}
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNonExistantObject() {
		log.error("El objeto a modificar en la base de datos no existe");
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNotFound() {
		log.error("No se ha encontrado");
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleEntryAlreadyExists() {
		log.info("No se puede actualizar/insertar la entrada por duplicidad de clave unica");
	}
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleNullPointer() {
		log.error("Fallo por objeto nulo");
	}
	
}
