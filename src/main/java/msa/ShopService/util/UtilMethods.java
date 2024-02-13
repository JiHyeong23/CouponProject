package msa.ShopService.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UtilMethods {

    public ResponseDto makeSuccessResponseDto(String message) {
        return ResponseDto.builder()
                .result(State.SUCCESS).message(message)
                .build();
    }

    public <T>ResponseDto makeSuccessResponseDto(String message, T response) {
        return ResponseDto.builder()
                .result(State.SUCCESS).message(message).response(response)
                .build();
    }

    public <T>ResponseDto makeFailResponseDto(String message, T response) {
        return ResponseDto.builder()
                .result(State.FAILED).message(message).response(response)
                .build();
    }
}
