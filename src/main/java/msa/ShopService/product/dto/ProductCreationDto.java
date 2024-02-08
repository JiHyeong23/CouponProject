package msa.ShopService.product.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductCreationDto {
    private String name;
    private Long price;
    private String description;
    private LocalDateTime openDate;
    private Long stock;
}
