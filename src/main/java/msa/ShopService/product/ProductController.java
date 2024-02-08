package msa.ShopService.product;

import lombok.AllArgsConstructor;
import msa.ShopService.product.dto.ProductCreationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    @PostMapping
    public void saveProduct(@RequestBody ProductCreationDto productCreationDto) {
        productService.saveProduct(productCreationDto);
    }
}
