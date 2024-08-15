package kr.kdev.demo.account;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Data
public class ContactRegisterRequest {
    private Long accountId;
    private List<@Valid Contact> contacts = new ArrayList<>();

    @Accessors(chain = true)
    @Data
    public static class Contact {
        private @NotBlank @Size(max = 30) String name;
        private @NotBlank @PhoneNumber String phone;
        private @NotBlank @Email String email;
    }
}
