package im.back.springboot.product.controller;

import im.back.springboot.data.dto.ChangeProductNameDTO;
import im.back.springboot.data.dto.ProductDTO;
import im.back.springboot.data.dto.ProductResponseDTO;
import im.back.springboot.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDTO> getProduct(Long id){
        ProductResponseDTO productResponseDTO = productService.getProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductResponseDTO productResponseDTO = productService.saveProduct(productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ChangeProductNameDTO changeProductDTO) throws Exception{
        ProductResponseDTO productResponseDTO = productService.changeProductName(changeProductDTO.getId(), changeProductDTO.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(@RequestParam Long id) throws Exception{
        productService.deleteProduct(id);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다");
    }
}
