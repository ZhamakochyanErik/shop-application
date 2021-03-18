package shop.application.service.interfaces;

import shop.application.dto.request.ProductCommentAddRequestDto;
import shop.application.dto.response.ProductCommentResponseDto;
import shop.application.model.User;

public interface ProductCommentService {

    ProductCommentResponseDto add(ProductCommentAddRequestDto productCommentAddRequestDto, User currentUser);
}
