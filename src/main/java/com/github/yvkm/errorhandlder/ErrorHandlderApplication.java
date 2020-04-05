package com.github.yvkm.errorhandlder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@SpringBootApplication
@Controller
public class ErrorHandlderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlderApplication.class, args);
    }

    @GetMapping("/makeerror")
    @ResponseBody
    public String makeArithmeticException() {
        int i = 1 / 0;
        return "error";
    }

    @GetMapping("/makeIOException")
    public String makeIOException() throws IOException {
        throw new IOException("读写错误");
    }


}
