package msa.ShopService.product;

import lombok.AllArgsConstructor;
import msa.ShopService.product.dto.ProductCreationDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    public void saveProduct(ProductCreationDto productCreationDto) {
        Product product = productMapper.dtoToEntity(productCreationDto);
        System.out.println(product);
        productRepository.save(product);
        System.out.println("@@@@" + product.getName());
    }
}
