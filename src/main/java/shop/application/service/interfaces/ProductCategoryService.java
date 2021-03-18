package shop.application.service.interfaces;

import shop.application.dto.request.ProductCategoryAddRequestDto;
import shop.application.dto.request.ProductCategoryUpdateRequestDto;
import shop.application.dto.response.ProductCategoryResponseDto;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryResponseDto> getAll();

    ProductCategoryResponseDto add(ProductCategoryAddRequestDto productCategoryAddRequestDto);

    ProductCategoryResponseDto update(ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto);

    void delete(long id);
}
