package kr.kdev.demo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/health")
    public Integer health(HttpServletRequest request,
                          @RequestHeader(HttpHeaders.USER_AGENT) String userAgent) {
        // NOTE: Supports forward proxy headers in the spring framework.
        String remoteAddr = request.getRemoteAddr();

        log.info("health check from {} with {}", remoteAddr, userAgent);
        return 200;
    }

}
