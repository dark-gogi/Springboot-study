package im.back.springboot.controller;

import im.back.springboot.data.dto.ValidatedRequestDTO;
import im.back.springboot.data.dto.ValidationRequestDTO;
import im.back.springboot.data.group.ValidationGroup1;
import im.back.springboot.data.group.ValidationGroup2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/validation")
public class ValidationController {

    private final Logger LOGGER = LoggerFactory.getLogger(ValidationController.class);

    @PostMapping(value = "/valid")
    public ResponseEntity<String> checkValidationByValid(
            @Valid @RequestBody ValidationRequestDTO validationRequestDTO){

        LOGGER.info(validationRequestDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validationRequestDTO.toString());
    }

    @PostMapping(value = "/validated")
    public ResponseEntity<String> checkValidation(
            @Validated @RequestBody ValidatedRequestDTO validatedRequestDTO){

        LOGGER.info(validatedRequestDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDTO.toString());
    }

    @PostMapping(value = "/validated/group1")
    public ResponseEntity<String> checkValidation1(
            @Validated(value = ValidationGroup1.class) @RequestBody ValidatedRequestDTO validatedRequestDTO){

        LOGGER.info(validatedRequestDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDTO.toString());

    }

    @PostMapping(value = "/validated/group2")
    public ResponseEntity<String> checkValidation2(
            @Validated(value = ValidationGroup2.class) @RequestBody ValidatedRequestDTO validatedRequestDTO){

        LOGGER.info(validatedRequestDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDTO.toString());

    }

    @PostMapping(value = "/validated/all-group")
    public ResponseEntity<String> checkValidation3(
            @Validated(value = {ValidationGroup1.class,ValidationGroup2.class}) @RequestBody ValidatedRequestDTO validatedRequestDTO){

        LOGGER.info(validatedRequestDTO.toString());

        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDTO.toString());

    }
}
