package shop.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRateResponseDto {

    private long id;

    private int rate;

    private LocalDateTime createdDate;

    private UserResponseDto user;
}
