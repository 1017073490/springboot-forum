package com.zhangxing.springbootforum.advice;

import com.alibaba.fastjson.JSON;
import com.zhangxing.springbootforum.dto.ResponseDTO;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/13 15:31
 */
@ControllerAdvice
public class CustomizerExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request,
                                           HttpServletResponse response,
                                           Throwable ex, Model model) {
        String contentType = request.getContentType();
        if (contentType.equals("application/json")) {
            ResponseDTO responseDTO;
            //返回json
            if (ex instanceof CustomizerException) {
                responseDTO = ResponseDTO.errorOf((CustomizerException) ex);
            } else {
                responseDTO = ResponseDTO.errorOf(CustomizerErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json; charset=UTF-8");
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(responseDTO));
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;

        } else {
            //錯誤頁面
            if (ex instanceof CustomizerException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizerErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
