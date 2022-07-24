package im.back.springboot.controller;

import com.google.gson.Gson;
import im.back.springboot.data.dto.ProductDTO;
import im.back.springboot.data.dto.ProductResponseDTO;
import im.back.springboot.service.impl.ProductServiceWithoutDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // 기존에 주입 받아야할 외부 객체에 대해 Mock 객체로 생성
    @MockBean
    ProductServiceWithoutDAO productServiceWithoutDAO;

    @Test
    @DisplayName("MockMvc 를 통한 Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception{

        /* ProductServiceWithoutDAO 는 실제 객체가 아닌 Mock 객체이기 때문에 실제 행위를 수행하지 않음
           따라서 객체의 행위를 Mockito 의 given() 메소드를 통해 동작을 정의

           아래의 경우, ProductService 가 getProduct(Long id) 를 통해 ProductResponseDTO 객체를
           반환하는 행위를 정의
         */
        given(productServiceWithoutDAO.getProduct(123L)).willReturn(
                new ProductResponseDTO(123L,"pen",5000,5000));

        //쿼리 스트링은 String 타입으로 반환하기 때문
        String productId = "123";

        /*
        MockMvc 는  Controller 의 API 를 테스트하기 위해 사용된 객체
        서블릿 컨테이너의 구동 없이 가상의 MVC 환경에서 모의 HTTP 서블릿을 요청하는 유틸리티 클래스

        perform() 메소드는 서버로 URL 요청을 보내는 것처럼 통신 테스트 코드를 작성

        perform 은 Given-When-Then 에서 When-Then 의 구조를 가짐
         */
        mockMvc.perform(get("/product?id=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        // verify() 를 통해 지정된 메소드가 실행됐는지 검증
        verify(productServiceWithoutDAO).getProduct(123L);
    }

    @Test
    @DisplayName(value = "CREATE PRODUCT TEST")
    void createProductTest() throws Exception{

        /*
        Mock 객체에서 특정 메소드가 실행되는 경우 실제 return 을 줄 수 없기 때문에 아래와 같이
        가정 사항을 만들어 줌
         */
        given(productServiceWithoutDAO.saveProduct(new ProductDTO("pen", 5000, 2000)))
                .willReturn(new ProductResponseDTO(12315L, "pen", 5000, 2000));

        ProductDTO productDTO = ProductDTO.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDTO);

        mockMvc.perform(post("/product").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productServiceWithoutDAO).saveProduct(new ProductDTO("pen", 5000, 2000));
    }

}
