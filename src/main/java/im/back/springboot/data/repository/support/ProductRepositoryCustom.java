package im.back.springboot.data.repository.support;

import im.back.springboot.data.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findByName(String name);
}
