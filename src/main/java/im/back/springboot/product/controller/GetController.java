package im.back.springboot.product.controller;

import im.back.springboot.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/v1/get-api")
@RestController
public class GetController {

    //http://localhost:8080/api/v1/get-api/hello
    @GetMapping(value = "/hello")
    public String getHello(){
        return "HTTP Get Method Controller";
    }

    //http://localhost:8080/api/v1/get-api/variable1/{String 값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    //http://localhost:8080/api/v1/get-api/request?name=value1&email=value2&organization=value3
    @GetMapping(value = "/request")
    public String getRequestParam(@RequestParam String name, @RequestParam String email, @RequestParam String org){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(" ").append(email).append(" ").append(org);

        return stringBuilder.toString();
    }

    //http://localhost:8080/api/v1/get-api/request2?name=value1&email=value2&organization=value3
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder stringBuilder = new StringBuilder();
        param.entrySet().forEach(map->{
            stringBuilder.append(map.getKey()+" : "+map.getValue()+"\n");
                });
        return stringBuilder.toString();
    }

    //http://localhost:8080/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    @GetMapping(value = "/request3")
    public String getRequestParam3(@ModelAttribute MemberDTO memberDto){
        return memberDto.toString();
    }


}
