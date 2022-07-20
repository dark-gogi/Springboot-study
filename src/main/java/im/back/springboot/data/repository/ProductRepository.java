package im.back.springboot.data.repository;

import im.back.springboot.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


//JpaRepository 의 모든 메소드는 내부적으로 @Transactional 을 구현하고 있음
public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findAllByName(String name);
    Product queryById(Long id);

    long countByName(String name);

    void deleteById(Long id);
    long removeByName(String name);

    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    Product findByIdIsNot(Long id);

    // is null, is not null
    List<Product> findByUpdateAtIsNull();
    List<Product> findByUpdateAtIsNotNull();

    // compare
    List<Product> findByPriceIsGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThanEqual(Long price);
    List<Product> findByStockIsLessThanEqual(Long stock);

    // like
    List<Product> findByNameLike(String name);
    List<Product> findByNameIsStartingWith(String name);

    // order by
    List<Product> findByNameOrderByIdAsc(String name);
    List<Product> findByNameOrderByIdDesc(String name);

    // Sort class
    List<Product> findByName(String name, Sort sort);

    Page<Product> findByName(String name, Pageable pageable);


    /*
    JPQL 을 활용한 객체 지향 쿼리

    @Query 내에 문자열을 정의하기 때문에 컴파일 타임에 에러를 확인할 수 없음
     */
    @Query(value = "SELECT P FROM Product AS P WHERE P.name = ?1")
    List<Product> findByName(String name);

    @Query("SELECT P FROM Product AS P WHERE P.name = :name")
    List<Product> findByNameParam(@Param("name") String name);




}
