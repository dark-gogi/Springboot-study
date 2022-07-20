package im.back.springboot.data.repository.support;

import im.back.springboot.data.entity.Product;
import im.back.springboot.data.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl(){
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name) {

        QProduct product = QProduct.product;

        /*
        QueryDslRepositorySupport 는 builder 패턴으로
        Query 를 지원하므로, 기존의 SQL Query 와 같이 순서를 지키지 않아도 됨
         */
        List<Product> productList = from(product)
                .where(product.name.eq(name))
                .select(product)
                .fetch();

        return productList;
    }
}
