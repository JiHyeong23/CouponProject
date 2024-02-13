package msa.ShopService.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto<T> {
    private State result;
    private String message;
    private T response;
}
