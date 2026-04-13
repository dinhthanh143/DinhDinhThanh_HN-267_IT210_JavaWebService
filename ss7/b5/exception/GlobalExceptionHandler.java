package org.example.ss07.bai5.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException ex, Model model) {
        if (ex.getMessage() != null && ex.getMessage().contains("exceeds maximum size")) {
            model.addAttribute("error", "File quá lớn! Vui lòng chọn file nhỏ hơn 10MB.");
            return "error";
        }
        
        model.addAttribute("error", "Lỗi: " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("error", "Đã xảy ra lỗi: " + ex.getMessage());
        return "error";
    }
}
