package im.back.springboot.data.repository.support;

import im.back.springboot.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "ProductRepo")
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{
}
