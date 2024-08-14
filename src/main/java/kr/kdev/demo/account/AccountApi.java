package kr.kdev.demo.account;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountApi {

    @PutMapping("/password")
    public Boolean updatePassword(@Validated @RequestBody PasswordUpdateRequest request) {
        return request.isEqualNewPasswordAndConfirmPassword();
    }
}
