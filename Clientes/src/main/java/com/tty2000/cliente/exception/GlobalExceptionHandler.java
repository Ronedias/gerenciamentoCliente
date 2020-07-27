package com.tty2000.cliente.exception;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.tty2000.cliente.exception.ErrorDetails.Campo;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleNegocio(ResourceNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		ErrorDetails details = new ErrorDetails(null, null);
		details.setTitulo(ex.getMessage());
		details.setTimestamp(new Date());
		details.setStatus(status.value());

		return handleExceptionInternal(ex, details, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ArrayList<Campo> errorDetails = new ArrayList<ErrorDetails.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nomeCampo = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			errorDetails.add(new ErrorDetails.Campo(nomeCampo, mensagem));
		}

		ErrorDetails details = new ErrorDetails(null, null);
		details.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		details.setTimestamp(new Date());
		details.setCampos(errorDetails);
		details.setStatus(status.value());

		return super.handleExceptionInternal(ex, details, headers, status, request);
	}

	/*
	 * @ExceptionHandler(ResourceNotFoundException.class) public ResponseEntity<?>
	 * resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	 * ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new ResponseEntity<>(errorDetails,
	 * HttpStatus.NOT_FOUND); }
	 * 
	 * @ExceptionHandler(Exception.class) public ResponseEntity<?>
	 * globleExcpetionHandler(Exception ex, WebRequest request) { ErrorDetails
	 * errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new ResponseEntity<>(errorDetails,
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

}
