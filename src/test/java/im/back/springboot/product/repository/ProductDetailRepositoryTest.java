package im.back.springboot.product.repository;

import im.back.springboot.data.entity.Product;
import im.back.springboot.data.entity.ProductDetail;
import im.back.springboot.data.repository.ProductDetailRepository;
import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveAndReadTest(){

        /* GIVEN */
        Product product = Product.builder()
                .name("Spring Boot JPA")
                .price(5000)
                .stock(500)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        productRepository.save(product);

        ProductDetail productDetail = ProductDetail.builder()
                .product(product)
                .description("Spring Boot And JPA Core Guide")
                .build();

        productDetailRepository.save(productDetail);

        /* WHEN-THEN */
        System.out.println("savedProduct : " + productDetailRepository.findById(
                productDetail.getId()).get().getProduct());

    }
}
