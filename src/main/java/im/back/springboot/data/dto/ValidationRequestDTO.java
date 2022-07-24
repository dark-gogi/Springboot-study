package im.back.springboot.data.dto;

import im.back.springboot.config.annotation.Telephone;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidationRequestDTO {

    @NotBlank
    String name;

    @NotBlank
    @Email
    String email;

    @Telephone
    String phoneNumber;

    @Min(value = 20) @Max(value = 50)
    int age;

    @Size(min = 0, max = 40)
    String description;

    @Positive
    int count;

    @AssertTrue
    boolean booleanCheck;


}
