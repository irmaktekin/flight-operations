package irmak.tekin.flight.management.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Comparator;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn(ex.getMessage(), ex);

        var response = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> new ErrorDetailDto("VALIDATION",fieldError.getField()+" "+fieldError.getDefaultMessage()))
                .sorted(Comparator.comparing(ErrorDetailDto::getMessage))
                .collect(Collectors.toList());;

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<Object> handlePaymentFailed(final Exception e, final WebRequest request) {
        log.error("handleAll {}; ", request, e);
        ErrorDetailDto errorResponse = new ErrorDetailDto("VALIDATION", "Payment Failed!.");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<Object> handleAlreadyReserved(final Exception e, final WebRequest request) {
        log.error("handleAll {}; ", request, e);
        ErrorDetailDto errorResponse = new ErrorDetailDto("VALIDATION", "Already reserved.");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<Object> handleFlightNotFound(final Exception e, final WebRequest request) {
        log.error("handleAll {}; ", request, e);
        ErrorDetailDto errorResponse = new ErrorDetailDto("NOTFOUND", "Seat Not Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Object> handleSeatNotFound(final Exception e, final WebRequest request) {
        log.error("handleAll {}; ", request, e);
        ErrorDetailDto errorResponse = new ErrorDetailDto("NOTFOUND", "Flight Not Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(final Exception e, final WebRequest request) {
        log.error("handleAll {}; ", request, e);
        return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }




}

