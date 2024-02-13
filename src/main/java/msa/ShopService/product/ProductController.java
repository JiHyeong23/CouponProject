package msa.ShopService.product;

import lombok.AllArgsConstructor;
import msa.ShopService.product.dto.GetProductDto;
import msa.ShopService.product.dto.ProductCreationDto;
import msa.ShopService.util.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    @PostMapping
    public void saveProduct(@RequestBody ProductCreationDto productCreationDto) {
        productService.saveProduct(productCreationDto);
    }

    @GetMapping
    public ResponseEntity getProduct(@RequestBody GetProductDto getProductDto) {
        ResponseDto responseDto = productService.getProductDetail(getProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/stock")
    public ResponseEntity getProductStock(@RequestBody GetProductDto getProductDto) {
        ResponseDto responseDto = productService.getProductStock(getProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
