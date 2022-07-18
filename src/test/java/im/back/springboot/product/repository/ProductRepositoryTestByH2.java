package im.back.springboot.product.repository;

import im.back.springboot.data.entity.Product;
import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepositoryTestByH2 {

    /*
    @DataJpaTest 의 특징과 기능은 다음과 같다

    1. JPA 관련 설정만 로드해서 테스트를 진행
    2. 기본적으로 @Transactional 어노테이션을 포함하여 테스트 코드가 종료되면 rollback 을 진행
    3. 기본값으로 임베디드 데이터베이스를 사용

     */
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest(){

        //given
        Product product = Product.builder()
                .name("펜")
                .price(1000)
                .stock(1000)
                .build();

        //when
        Product savedProduct  = productRepository.save(product);

        //then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(),savedProduct.getPrice());
        assertEquals(product.getStock(),savedProduct.getStock());
    }

    @Test
    void selectTest(){

        //given
        Product product = Product.builder()
                .name("펜")
                .price(1000)
                .stock(1000)
                .build();

        Product savedProduct = productRepository.saveAndFlush(product);

        //when
        Product foundProduct = productRepository.findById(savedProduct.getId()).get();

        assertEquals(product.getName(),savedProduct.getName());
        assertEquals(product.getPrice(),savedProduct.getPrice());
        assertEquals(product.getStock(),savedProduct.getStock());

    }

}
