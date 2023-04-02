package com.example.managephone.exception;

import com.example.managephone.constant.ErrorCodeDefs;
import com.example.managephone.response.BaseResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {
    @ResponseStatus(OK)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public BaseResponse methodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        BaseResponse baseResponse = BaseResponse.builder()
                .success(false)
                .error(processFieldError(fieldErrorList))
                .build();
        return baseResponse;
    }

    public BaseResponse.Error processFieldError(List<FieldError> fieldErrors){
        BaseResponse.Error error = BaseResponse.Error.builder()
                .code(ErrorCodeDefs.VADIDATION_ERROR)
                .message(ErrorCodeDefs.getErrMsg(ErrorCodeDefs.VADIDATION_ERROR))
                .build();
        List<BaseResponse.ErrorDetail> errorDetailList = new ArrayList<>();
        for(FieldError fieldError: fieldErrors){
                BaseResponse.ErrorDetail errorDetail = BaseResponse.ErrorDetail.builder()
                        .id(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build();
                errorDetailList.add(errorDetail);
        }
        error.setErrorDetailList(errorDetailList);
        return error;
    }

    @ResponseStatus(OK)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public BaseResponse notReadableException(HttpMessageNotReadableException ex){
        return BaseResponse.builder()
                .success(false)
                .error(BaseResponse.Error.builder()
                        .code(ErrorCodeDefs.VADIDATION_ERROR)
                        .message(ErrorCodeDefs.getErrMsg(ErrorCodeDefs.VADIDATION_ERROR))
                        .build())
                .build();
    }

    @ResponseStatus(OK)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse methodArgumentNotValidException(Exception ex){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setFalsed(ErrorCodeDefs.SERVER_ERROR, ex.getMessage());
        return baseResponse;
    }

}
