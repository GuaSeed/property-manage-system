package edu.xihua.project.pms.interceptor;

import com.google.gson.Gson;
import edu.xihua.project.pms.util.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<?> globalException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        logger.error("ip: {}, url: {}, message: {}",
                IPUtils.getIpAddr(request), request.getRequestURI(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Gson().toJson(e.getMessage()));
    }
}
