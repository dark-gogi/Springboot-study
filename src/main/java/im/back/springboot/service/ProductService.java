package im.back.springboot.service;

import im.back.springboot.data.dto.ProductDTO;
import im.back.springboot.data.dto.ProductResponseDTO;

public interface ProductService {

    ProductResponseDTO getProduct(Long id);
    ProductResponseDTO saveProduct(ProductDTO productDTO);
    ProductResponseDTO changeProductName(Long id, String name) throws Exception;
    void deleteProduct(Long id) throws Exception;


}
