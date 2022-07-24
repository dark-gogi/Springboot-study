package im.back.springboot.service.impl;

import im.back.springboot.data.dto.ProductDTO;
import im.back.springboot.data.dto.ProductResponseDTO;
import im.back.springboot.data.entity.Product;
import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    /*
    단위 테스트를 위해서는 외부 요인을 모두 배제하도록 코드를 작성
    @SpringBootTest, @WebMvcTest 등의 통합 / 슬라이스 테스트 어노테이션을 선언하지 않음
     */

    // Mockito 를 통해 ProductRepository.class 를 주입받음
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceWithoutDAO productService;

    // ProductService 에 ProductRepository 의존 주입
    @BeforeEach
    public void setUpTest(){
        productService = new ProductServiceWithoutDAO(productRepository);
    }

    @Test
    void getProductTest(){

        // Given
        Product givenProduct = Product.builder()
                .id(123L)
                .name("펜")
                .price(1000)
                .stock(1234)
                .build();

        // When-Then
        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        ProductResponseDTO productResponseDTO = productService.getProduct(123L);

        // 값에 대한 검증
        assertEquals(productResponseDTO.getId(), givenProduct.getId());
        assertEquals(productResponseDTO.getName(), givenProduct.getName());
        assertEquals(productResponseDTO.getPrice(), givenProduct.getPrice());
        assertEquals(productResponseDTO.getStock(), givenProduct.getStock());

        /*
        verify(T t) 는 주입 받은 타입 파라미터의 타입에 해당하는 Mock 객체를 반환
        해당 Mock 객체의 메소드를 사용하여 검증할 수 있음
         */
        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest(){

        /*
        Given-When-Then

        ProductRepository 에 Product 객체가 save 될 때, 첫번째 객체를 반환
         */
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        ProductResponseDTO productResponseDTO = productService.saveProduct(
                new ProductDTO("펜", 1000, 1234));

        assertEquals(productResponseDTO.getName(), "펜");
        assertEquals(productResponseDTO.getPrice(),1000);
        assertEquals(productResponseDTO.getStock(),1234);

        /*
        any() 는 ArgumentMatchers 에서 제공하는 메소드
        Mock 객체의 동작을 정의하거나 검증하는 단계에서 조건으로 특정 매개변수의 전달을 설정하지 않고,
        메소드의 실행만을 확인하거나 좀 더 큰 범위의 클래스 객체를 매개변수로 전달받는 등의 상황에 사용
         */
       verify(productRepository).save(any());

    }


}
