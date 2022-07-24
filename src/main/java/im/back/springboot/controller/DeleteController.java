package im.back.springboot.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/delete-api")
@RestController
public class DeleteController {

    //http://localhost:8080/api/v1/delete-api/{value}
    @DeleteMapping(value = "/{variable}")
    public String deleteVariable(@PathVariable String variable){
        return variable;
    }

    //http://localhost:8080/api/v1/delete-api/request
    @DeleteMapping(value = "/request")
    public String deleteRequestParam(@RequestParam String email){
        return email;
    }
}
