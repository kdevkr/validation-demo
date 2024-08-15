package kr.kdev.demo.account;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountApi {

    @PutMapping("/password")
    public Boolean updatePassword(@Validated @RequestBody PasswordUpdateRequest request) {
        return request.isEqualNewPasswordAndConfirmPassword();
    }

    @PostMapping("/contact")
    public Boolean registerContact(@Validated @RequestBody ContactRegisterRequest request) {
        return !request.getContacts().isEmpty();
    }
}
