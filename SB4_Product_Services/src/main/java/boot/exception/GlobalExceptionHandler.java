package boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<String> arithmeticExcepHandler(ArithmeticException arithmeticException) {
		System.out.println("ArithmeticException Handled Globally");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(arithmeticException.getMessage());
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> nullPointExcepHandler(NullPointerException nullPointerException) {
		System.out.println("NullPointerException Handled Globally");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nullPointerException.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> excepHandler(Exception exception) {
		System.out.println("Exception Handled Globally");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
