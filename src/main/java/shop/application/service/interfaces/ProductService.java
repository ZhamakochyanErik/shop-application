package shop.application.service.interfaces;

import shop.application.dto.request.ProductAddRequestDto;
import shop.application.dto.request.ProductUpdateRequestDto;
import shop.application.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAll();

    ProductResponseDto getById(long id);

    ProductResponseDto add(ProductAddRequestDto productAddRequestDto);

    ProductResponseDto update(ProductUpdateRequestDto productUpdateRequestDto);

    void delete(long id);

    List<ProductResponseDto> search(String name, Double fromPrice, Double toPrice, Integer rate);
}
