package im.back.springboot.common.exception;

import im.back.springboot.common.Constants;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    private final Constants.ExceptionClass exceptionClass;
    private final HttpStatus httpStatus;

    public CustomException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus,
                           String message){
        super(exceptionClass.toString() + message);

        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass(){
        return exceptionClass;
    }

    public int getHttpStatusCode(){
        return httpStatus.value();
    }

    public String getHttpStatusType(){
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
