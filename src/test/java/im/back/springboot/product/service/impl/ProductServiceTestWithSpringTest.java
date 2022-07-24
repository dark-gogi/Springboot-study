package im.back.springboot.product.service.impl;

import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = SpringExtension.class)
@Import(ProductServiceWithoutDAO.class)
public class ProductServiceTestWithSpringTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductServiceWithoutDAO productServiceWithoutDAO;

    // Test Code Emit
}
