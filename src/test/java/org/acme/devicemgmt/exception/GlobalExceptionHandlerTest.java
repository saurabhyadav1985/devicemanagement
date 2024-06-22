package org.acme.devicemgmt.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    void shouldHandleIllegalArgumentException(){
        // given
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

        // when
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleIllegalArgumentException(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid argument", ((ApiError) responseEntity.getBody()).getMessage());
    }

}
