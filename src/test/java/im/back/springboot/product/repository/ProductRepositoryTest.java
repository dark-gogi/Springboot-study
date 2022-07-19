package im.back.springboot.product.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import im.back.springboot.data.entity.Product;
import im.back.springboot.data.entity.QProduct;
import im.back.springboot.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ProductRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest(){

        /*
        JPA Auditing 으로 관리하게 되는 경우,
        기존 Entity 의 Builder Pattern 으로 사용할 수 없다
        Lombok @SuperBuilder 로 지원
         */

        Product product = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        Product product2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        // price 기준으로 오름차순 sort
        productRepository.findByName("펜", Sort.by(Order.asc("price")));

        // price 기준으로 오름차순, stock 기준으로 내림차순
        productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock")));

        // Paging 처리
        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0,2));
    }

    @Test
    void queryDslTest(){
        JPAQuery<Product> query = new JPAQuery<>(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
        .from(qProduct)
        .where(qProduct.name.eq("펜"))
        .orderBy(qProduct.price.asc())
        .fetch();

        for(Product product : productList){
            System.out.println("=================");
            System.out.println();
            System.out.println("Product Id:" + product.getId());
            System.out.println("Product Name:" + product.getName());
            System.out.println("Product Price:" + product.getPrice());
            System.out.println("Product Stock:" + product.getStock());
            System.out.println();
            System.out.println("=================");
        }
    }

    @Test
    void queryDslTest2(){

        //given
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product : productList){
            System.out.println("=================");
            System.out.println();
            System.out.println("Product Id:" + product.getId());
            System.out.println("Product Name:" + product.getName());
            System.out.println("Product Price:" + product.getPrice());
            System.out.println("Product Stock:" + product.getStock());
            System.out.println();
            System.out.println("=================");
        }

    }

    @Test
    void queryDslTest3(){

        //given
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory.select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList){
            System.out.println("=================");
            System.out.println("Product Name:" + product);
            System.out.println("=================");
        }
    }

    @Test
    void queryDslTest4(){

        //given
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;
        
        // Tuple 의 경우 QDomain 의 static field 를 바로 사용
        List<Tuple> productList = jpaQueryFactory.select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Tuple tuple : productList){
            System.out.println("=================");
            System.out.println("Product Name:" + qProduct.name);
            System.out.println("Product Price:" + qProduct.price);
            System.out.println("=================");
        }
    }

}
