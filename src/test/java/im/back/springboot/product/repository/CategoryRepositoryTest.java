package im.back.springboot.product.repository;

import im.back.springboot.data.entity.Category;
import im.back.springboot.data.entity.Product;
import im.back.springboot.data.repository.CategoryRepository;
import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest(){

        Product product = new Product();

        product.setName("수박");
        product.setPrice(19900);
        product.setStock(100);

        productRepository.save(product);

        Category category = new Category();

        category.setCode("500");
        category.setName("청과");
        category.getProducts().add(product);

        categoryRepository.save(category);

        //Test

        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for(Product foundProduct : products){
            System.out.println(foundProduct);
        }

    }
}
