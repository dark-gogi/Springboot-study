package im.back.springboot.product.repository;

import im.back.springboot.data.entity.Product;
import im.back.springboot.data.entity.Provider;
import im.back.springboot.data.repository.ProductRepository;
import im.back.springboot.data.repository.ProviderRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void cascadeTest(){

     Provider provider = savedProvider("만수홀딩스");

     Product product = savedProduct("반도체",200000,100);
     Product product2 = savedProduct("CPU",250000,50);
     Product product3 = savedProduct("메모리",100000,150);

     product.setProvider(provider);
     product2.setProvider(provider);
     product3.setProvider(provider);

     /*
        newArrayList 는 기존 list 에 입력받은 파라미터를 Collections.addAll 하여
        list 를 반환
      */
     provider.getProductList().addAll(Lists.newArrayList(product,product2,product3));

     providerRepository.save(provider);

    }

    private Provider savedProvider(String name){
        Provider provider = new Provider();
        provider.setName(name);

        return provider;
    }

    private Product savedProduct(String name, int price, int stock){

        Product product = new Product();

        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    @Test
    @Transactional
    public void orphanRemovalTest(){

        Provider provider = savedProvider("만수홀딩스");

        Product product = savedProduct("반도체",200000,100);
        Product product2 = savedProduct("CPU",250000,50);
        Product product3 = savedProduct("메모리",100000,150);

        product.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

     /*
        newArrayList 는 기존 list 에 입력받은 파라미터를 Collections.addAll 하여
        list 를 반환
      */
        provider.getProductList().addAll(Lists.newArrayList(product,product2,product3));

        providerRepository.saveAndFlush(provider);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        providerRepository.findAll().forEach(System.out::println);
        productRepository.findAll().forEach(System.out::println);

    }

}
