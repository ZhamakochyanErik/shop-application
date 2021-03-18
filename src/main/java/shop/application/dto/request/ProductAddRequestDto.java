package shop.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAddRequestDto {

    private String name;

    private double price;

    private String description;

    private List<Long> categoryList = new ArrayList<>();
}
