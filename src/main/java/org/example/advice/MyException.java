package org.example.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyException extends RuntimeException {

    private ExceptionEnums exceptionEnums;
}
