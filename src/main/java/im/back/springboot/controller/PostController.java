package im.back.springboot.controller;

import im.back.springboot.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(value = "/api/v1/post-api")
@RestController
public class PostController {

    //http://localhost:8080/api/v1/post-api/domain
    @PostMapping(value = "/domain")
    public String postExample(){
        return "Hello Post API";
    }

    //http://localhost:8080/api/v1/post-api/member
    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String, String> postData){
        StringBuilder stringBuilder = new StringBuilder();

        postData.entrySet().forEach(map->{
            stringBuilder.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
        });

        return stringBuilder.toString();
    }

    //http:localhost:8080/api/v1/post-api/member2
    @PostMapping(value = "/member2")
    public String postMember2(@RequestBody MemberDTO memberDto){
        return memberDto.toString();
    }

}
