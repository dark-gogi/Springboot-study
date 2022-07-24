package im.back.springboot.product.repository;

import im.back.springboot.data.entity.Product;
import im.back.springboot.data.entity.Provider;
import im.back.springboot.data.repository.ProductRepository;
import im.back.springboot.data.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    public void relationshipTest(){

        //GIVEN
        Provider provider = new Provider();
        provider.setName("만수물산");

        providerRepository.save(provider);

        Product product = new Product();

        product.setName("투움바 파스타");
        product.setPrice(18000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println(
                "product : " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new)
        );

        System.out.println(
                "provider : " + productRepository.findById(1L)
                .orElseThrow(RuntimeException::new).getProvider()
        );

    }

    @Test
    void relationshipTest2(){
        //Given
        Provider provider = new Provider();
        provider.setName("남동상회");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("밀가루");
        product1.setPrice(5000);
        product1.setStock(100);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("토마토");
        product2.setPrice(2900);
        product2.setStock(200);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("트러플");
        product3.setPrice(100000);
        product3.setStock(5);
        product3.setProvider(provider);

        Product product4 = new Product();
        product4.setName("삼겹살");
        product4.setPrice(19900);
        product4.setStock(50);
        product4.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        List<Product> productList = providerRepository.findById(provider.getId())
                .get().getProductList();

        for(Product product : productList){
            System.out.println(product);
        }

    }

}
