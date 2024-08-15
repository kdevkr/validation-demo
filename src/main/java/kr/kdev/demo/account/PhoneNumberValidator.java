package kr.kdev.demo.account;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final PhoneNumberUtil instance = PhoneNumberUtil.getInstance();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        try {
            // NOTE: It does not detect when a whitespace is included.
            Phonenumber.PhoneNumber phoneNumber = instance.parse(value, null);
            String result = instance.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
            return result != null && !result.trim().isEmpty();
        } catch (NumberParseException e) {
            return false;
        }
    }
}
