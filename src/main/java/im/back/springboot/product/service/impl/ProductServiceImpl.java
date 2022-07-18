package im.back.springboot.product.service.impl;

import im.back.springboot.data.dao.ProductDAO;
import im.back.springboot.data.dto.ProductDTO;
import im.back.springboot.data.dto.ProductResponseDTO;
import im.back.springboot.data.entity.Product;
import im.back.springboot.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDTO getProduct(Long id) {
        Product product = productDAO.selectProduct(id);
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO saveProduct(ProductDTO productDTO) {

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());

        //insert 결과 조회되는 id 값을 가져오기 위해 필요
        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO changeProductName(Long id, String name) throws Exception {

        Product changedProduct = productDAO.updateProductName(id, name);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                changedProduct.getId(),
                changedProduct.getName(),
                changedProduct.getPrice(),
                changedProduct.getStock());


        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        productDAO.deleteProduct(id);
    }
}
