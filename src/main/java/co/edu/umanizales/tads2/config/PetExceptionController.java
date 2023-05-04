package co.edu.umanizales.tads2.config;

import co.edu.umanizales.tads2.controller.dto.ErrorDTO;
import co.edu.umanizales.tads2.controller.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
@Validated
public class PetExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ErrorDTO> errorsDTOList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(),
                    fieldError.getField() + ": " + fieldError.getDefaultMessage());
            errorsDTOList.add(errorDTO);
        }

        return new ResponseEntity<>(new ResponseDTO(400, "Se produjo un error", errorsDTOList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyNullPointerException.class)
    public ResponseEntity<ResponseDTO> resourceNotFoundException(MyNullPointerException e) {
        List<ErrorDTO> listMessage = e.getElements();

        return new ResponseEntity<ResponseDTO>(new ResponseDTO(400, "Se produjo un error", listMessage), HttpStatus.BAD_REQUEST);
    }
}
