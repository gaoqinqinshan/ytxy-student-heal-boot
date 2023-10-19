package org.example.advice;

import lombok.Data;

/**
 * 异常处理
 */
@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums enums) {
        this.status = enums.getCode();
        this.message = enums.getMsg();
        this.timestamp = System.currentTimeMillis();
    }

}
