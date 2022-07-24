package im.back.springboot.service.impl;

import im.back.springboot.data.dto.ProductDTO;
import im.back.springboot.data.dto.ProductResponseDTO;
import im.back.springboot.data.entity.Product;
import im.back.springboot.data.repository.ProductRepository;
import im.back.springboot.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceWithoutDAO implements ProductService {

    private final ProductRepository productRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceWithoutDAO.class);

    @Autowired
    public ProductServiceWithoutDAO(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO getProduct(Long id) {

        LOGGER.info("[getProduct] : input id : {}" , id);

        Product product = productRepository.findById(id).get();

        LOGGER.info("[getProduct] : product id : {}, name : {}",product.getId(),product.getName());

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStock(product.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO saveProduct(ProductDTO productDTO) {

        LOGGER.info("[savedProduct] productDTO : {}", productDTO.toString());
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());

        //insert 결과 조회되는 id 값을 가져오기 위해 필요
        Product savedProduct = productRepository.save(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStock());

        return productResponseDTO;
    }

    @Override
    public ProductResponseDTO changeProductName(Long id, String name) throws Exception {

        Product foundProduct = productRepository.findById(id).get();
        foundProduct.setName(name);
        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                changedProduct.getId(),
                changedProduct.getName(),
                changedProduct.getPrice(),
                changedProduct.getStock());


        return productResponseDTO;
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        productRepository.deleteById(id);
    }
}
