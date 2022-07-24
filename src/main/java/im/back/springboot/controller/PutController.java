package im.back.springboot.controller;

import im.back.springboot.data.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(value = "/api/v1/put-api")
@RestController
public class PutController {

    //http://localhost:8080/api/v1/put-api/member
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, String> putData){
        StringBuilder stringBuilder = new StringBuilder();

        putData.entrySet().forEach(map->{
            stringBuilder.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
        });

        return stringBuilder.toString();
    }

    //http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public String putMemberDto(@RequestBody MemberDTO memberDto){
        return memberDto.toString();
    }

    //http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member3")
    public MemberDTO putMemberDto2(@RequestBody MemberDTO memberDto){
        return memberDto;
    }

    //http://localhost:8080/api/v1/put-api/member3
    @PutMapping(value = "/member4")
    public ResponseEntity<MemberDTO> putMemberDto3(@RequestBody MemberDTO memberDto){

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
