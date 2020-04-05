package com.github.yvkm.errorhandlder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xie jian xun
 * @since
 */
@ControllerAdvice
public class ErrorController {

    /************************** 注意 ***********************/
    /*
     * 详见@ExceptionHandler
     *
     * 方法的入参可以有多个，并且可以是任意顺序
     * - Request、Response对象
     * - Session对象
     * - WebRequest对象
     * - NativeWebRequest对象
     * - Local对象
     * - Reader / Writer
     * - Model
     * 返回值同样可以有多种
     * - ModelAndView
     * - Model
     * - Map
     * - View
     * - String
     * - ResponseEntity
     * - void, 当方法直接对Response对象进行直接响应时
     */
    /*****************************************************************/

    // 返回Json
    // 用于指定这个方法指定处理的是哪个异常
    // @ExceptionHandler(Exception.class) // 捕获更多的内容
    @ExceptionHandler(ArithmeticException.class)
    public final ResponseEntity<ErrorResponse> handleArithmeticException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        // 实体的内容
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details);
        // ResponseEntity是响应的实体,参数分别为 实体内容 和 响应码.
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 返回html
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("hello", "world");
        model.addObject("message", ex.getMessage());
        return model;
    }
}
