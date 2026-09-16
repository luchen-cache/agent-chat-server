package com.wzh.common;

import com.wzh.model.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author luchen
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     *
     * @param exception 业务异常
     * @return 统一异常响应
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResultVO<Void>> handleBusinessException(BusinessException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResultVO.error(exception.getCode(), exception.getMessage()));
    }

    /**
     * 处理参数校验异常
     *
     * @param exception 参数校验异常
     * @return 统一异常响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultVO<Void>> handleValidationException(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .orElse(null);
        String message = fieldError == null ? "请求参数不合法" : fieldError.getDefaultMessage();
        return ResponseEntity.badRequest().body(ResultVO.error(40000, message));
    }
}
