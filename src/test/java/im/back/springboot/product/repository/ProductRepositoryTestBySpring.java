package im.back.springboot.product.repository;

import im.back.springboot.data.entity.Product;
import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTestBySpring {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void basicCRUDTest(){

        /* create */

        //given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();

        //when
        Product savedProduct = productRepository.save(givenProduct);

        //then
        assertThat(savedProduct.getId()).isEqualTo(givenProduct.getId());
        assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        /* read */

        Product selectedProduct = productRepository.findById(savedProduct.getId())
                .orElseThrow(RuntimeException::new);

        //then
        assertThat(selectedProduct.getId()).isEqualTo(givenProduct.getId());
        assertThat(selectedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(selectedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(selectedProduct.getStock()).isEqualTo(givenProduct.getStock());

        /* update */

        //when
        Product foundProduct = productRepository.findById(selectedProduct.getId())
                .orElseThrow(RuntimeException::new);

        foundProduct.setName("장난감");

        Product updatedProduct = productRepository.save(foundProduct);

        //then
        assertEquals(updatedProduct.getName(),"장난감");

        /* delete */

        //when
        productRepository.delete(updatedProduct);

        //then
        assertFalse(productRepository.findById(selectedProduct.getId()).isPresent());

    }
}
