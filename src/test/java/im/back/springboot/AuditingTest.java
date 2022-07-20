package im.back.springboot;

import im.back.springboot.data.entity.Product;
import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
public class AuditingTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void auditingTest(){

        Product product = Product.builder()
                .name("Apple")
                .price(1000)
                .stock(500)
                .build();

        Product savedProduct = productRepository.save(product);

        System.out.println(savedProduct.getName());
        System.out.println(savedProduct.getCreateAt());
    }
}
