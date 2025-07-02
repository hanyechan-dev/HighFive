package com.jobPrize.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobPrize.customException.CustomAccessDeniedException;
import com.jobPrize.customException.CustomApprovalDeniedException;
import com.jobPrize.customException.CustomDateFormatException;
import com.jobPrize.customException.CustomEmailDuplicateException;
import com.jobPrize.customException.CustomEntityNotFoundException;
import com.jobPrize.customException.CustomEnumMismatchException;
import com.jobPrize.customException.CustomIllegalArgumentException;
import com.jobPrize.customException.CustomNumberFormatException;
import com.jobPrize.customException.CustomOwnerMismatchException;
import com.jobPrize.customException.CustomSubscriptionDeniedException;
import com.jobPrize.dto.common.error.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomEmailDuplicateException.class)
    public ResponseEntity<ErrorResponse> handleEmailDuplicate(CustomEmailDuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("EMAIL_DUPLICATE", ex.getMessage()));
    }

    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(CustomAccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse("ACCESS_DENIED", ex.getMessage()));
    }

    @ExceptionHandler(CustomApprovalDeniedException.class)
    public ResponseEntity<ErrorResponse> handleApprovalDenied(CustomApprovalDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse("APPROVAL_DENIED", ex.getMessage()));
    }

    @ExceptionHandler(CustomSubscriptionDeniedException.class)
    public ResponseEntity<ErrorResponse> handleSubscriptionDenied(CustomSubscriptionDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse("SUBSCRIPTION_DENIED", ex.getMessage()));
    }

    @ExceptionHandler(CustomEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(CustomEntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("ENTITY_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(CustomOwnerMismatchException.class)
    public ResponseEntity<ErrorResponse> handleOwnerMismatch(CustomOwnerMismatchException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse("OWNER_MISMATCH", ex.getMessage()));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(new ErrorResponse("INVALID_INPUT", message));
    }
    
    @ExceptionHandler(CustomDateFormatException.class)
    public ResponseEntity<ErrorResponse> handleCustomDateFormatException(CustomDateFormatException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("INVALID_DATE_FORMAT", ex.getMessage()));
    }
    
    @ExceptionHandler(CustomEnumMismatchException.class)
    public ResponseEntity<ErrorResponse> handleEnumMismatch(CustomEnumMismatchException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("ENUM_MISMATCH", ex.getMessage()));
    }
    
    @ExceptionHandler(CustomNumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleCustomNumberFormat(CustomNumberFormatException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("INVALID_NUMBER_FORMAT", ex.getMessage()));
    }
    
    @ExceptionHandler(CustomIllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleCustomIllegalArgument(CustomIllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse("INVALID_ARGUMENT", ex.getMessage()));
    }
    
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
    	log.error("[예외발생]", ex); // 전체 스택트레이스 로그로 출력
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new ErrorResponse("INTERNAL_ERROR",ex.getClass().getName()+ ex.getMessage()));
    }
    
    
    



}

    



