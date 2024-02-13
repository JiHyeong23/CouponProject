package msa.ShopService.product;

import lombok.AllArgsConstructor;
import msa.ShopService.product.dto.GetProductDto;
import msa.ShopService.product.dto.ProductCreationDto;
import msa.ShopService.product.dto.ProductDetailDto;
import msa.ShopService.util.ResponseDto;
import msa.ShopService.util.UtilMethods;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private UtilMethods utilMethods;

    public void saveProduct(ProductCreationDto productCreationDto) {
        Product product = productMapper.dtoToEntity(productCreationDto);
        productRepository.save(product);
    }

    public ResponseDto getProductDetail(GetProductDto getProductDto) {
        Product product = productRepository.findById(getProductDto.getProductId()).get(); //예외처리
        ProductDetailDto productDetailDto = productMapper.entityToDto(product);
        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully loaded", productDetailDto);
        return responseDto;
    }

    public ResponseDto getProductStock(GetProductDto getProductDto) {
        Product product = productRepository.findById(getProductDto.getProductId()).get();
        ResponseDto responseDto = utilMethods.makeSuccessResponseDto("Successfully loaded", product.getStock());
        return responseDto;
    }
}
