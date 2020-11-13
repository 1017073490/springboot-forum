package com.zhangxing.springbootforum.advice;

import com.zhangxing.springbootforum.exception.CustomizerErrorCode;
import com.zhangxing.springbootforum.exception.CustomizerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/13 15:31
 */
@ControllerAdvice
public class CustomizerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model) {
        HttpStatus status = getStatus(request);
        if (ex instanceof CustomizerException) {
            model.addAttribute("message", ex.getMessage());
        } else {
            model.addAttribute("message", "服務器冒煙了");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
