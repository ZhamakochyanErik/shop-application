package shop.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.application.dto.request.ProductCategoryAddRequestDto;
import shop.application.dto.request.ProductCategoryUpdateRequestDto;
import shop.application.dto.response.ProductCategoryResponseDto;
import shop.application.service.interfaces.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/product/category")
public class ProductCategoryEndpoint {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryEndpoint(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryResponseDto>> categories(){
        return ResponseEntity.ok(productCategoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProductCategoryResponseDto> add(@RequestBody ProductCategoryAddRequestDto productCategoryAddRequestDto){
        return ResponseEntity.ok(productCategoryService.add(productCategoryAddRequestDto));
    }

    @PutMapping
    public ResponseEntity<ProductCategoryResponseDto> update(@RequestBody ProductCategoryUpdateRequestDto productCategoryAddRequestDto){
        return ResponseEntity.ok(productCategoryService.update(productCategoryAddRequestDto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable("categoryId") long categoryId){
        productCategoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }
}
