package shop.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.application.dto.request.ProductCommentAddRequestDto;
import shop.application.dto.response.ProductCommentResponseDto;
import shop.application.model.User;
import shop.application.service.interfaces.ProductCommentService;

@RestController
@RequestMapping("/product/comment")
public class ProductCommentEndpoint {

    private final ProductCommentService productCommentService;

    public ProductCommentEndpoint(ProductCommentService productCommentService) {
        this.productCommentService = productCommentService;
    }

    @PostMapping
    public ResponseEntity<ProductCommentResponseDto> add(@RequestBody ProductCommentAddRequestDto productCommentAddRequestDto,
                                                         @AuthenticationPrincipal User user){
        return ResponseEntity.ok(productCommentService.add(productCommentAddRequestDto,user));
    }
}
