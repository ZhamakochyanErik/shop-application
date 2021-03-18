package shop.application.service.interfaces;

import shop.application.dto.request.ProductRateAddRequestDto;
import shop.application.dto.response.ProductRateResponseDto;
import shop.application.model.User;

public interface ProductRateService {

    ProductRateResponseDto add(ProductRateAddRequestDto productRateAddRequestDto, User currentUser);
}
