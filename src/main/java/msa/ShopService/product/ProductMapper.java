package msa.ShopService.product;

import msa.ShopService.product.dto.ProductCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product dtoToEntity(ProductCreationDto productCreationDto);
}
