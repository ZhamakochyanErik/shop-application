package shop.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.application.dto.request.ProductRateAddRequestDto;
import shop.application.dto.response.ProductRateResponseDto;
import shop.application.model.User;
import shop.application.service.interfaces.ProductRateService;

@RestController
@RequestMapping("/product/rate")
public class ProductRateEndpoint {

    private final ProductRateService productRateService;

    public ProductRateEndpoint(ProductRateService productRateService) {
        this.productRateService = productRateService;
    }

    @PostMapping
    public ResponseEntity<ProductRateResponseDto> add(@RequestBody ProductRateAddRequestDto productRateAddRequestDto,
                                                      @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(productRateService.add(productRateAddRequestDto, currentUser));
    }
}
