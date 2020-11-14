package com.zhangxing.springbootforum.dto;

import com.zhangxing.springbootforum.exception.CustomizerErrorCode;
import com.zhangxing.springbootforum.exception.CustomizerException;
import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/14 9:12
 */
@Data
public class ResponseDTO {
    private Integer code;
    private String message;

    public static ResponseDTO errorOf(Integer code, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(code);
        responseDTO.setMessage(message);
        return responseDTO;
    }

    public static ResponseDTO errorOf(CustomizerErrorCode error) {
        return errorOf(error.getCode(), error.getMessage());
    }

    public static ResponseDTO errorOf(CustomizerException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResponseDTO okOf() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(200);
        responseDTO.setMessage("成功");
        return responseDTO;
    }


}
