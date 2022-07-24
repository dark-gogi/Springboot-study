package im.back.springboot.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
    전화번호를 정규표현식으로 유효성 검사하는 @Regexp 가 자주 쓰이므로,
    Custom Validation 으로 지정하여 사용할 수 있음
 */
public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(value == null){
            return false;
        }

        return value.matches("01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$");
    }
}
