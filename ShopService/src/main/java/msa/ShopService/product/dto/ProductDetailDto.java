package msa.ShopService.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductDetailDto {
    private String name;
    private Long price;
    private String description;
    private LocalDateTime openDate;
    private Long stock;
}
