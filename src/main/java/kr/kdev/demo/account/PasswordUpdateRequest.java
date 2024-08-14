package kr.kdev.demo.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class PasswordUpdateRequest {
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{6,}$")
    private String password;
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{6,}$")
    private String newPassword;
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{6,}$")
    private String confirmPassword;

    @JsonIgnore
    @AssertTrue
    public boolean isEqualNewPasswordAndConfirmPassword() {
        return newPassword.equals(confirmPassword);
    }
}
