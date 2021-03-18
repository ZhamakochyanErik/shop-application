package shop.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private long id;

    private String name;

    private double price;

    private String description;

    private LocalDateTime createdDate;

    private List<ProductCommentResponseDto> productCommentList;

    private List<ProductRateResponseDto> productRateList;

    private List<ProductCategoryResponseDto> productCategoryList;
}
