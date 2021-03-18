package shop.application.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.application.dto.request.ProductAddRequestDto;
import shop.application.dto.request.ProductUpdateRequestDto;
import shop.application.dto.response.ProductResponseDto;
import shop.application.service.interfaces.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductEndpoint {

    private final ProductService productService;

    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> products() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> product(@PathVariable("productId") long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> add(@RequestBody ProductAddRequestDto productAddRequestDto) {
        return ResponseEntity.ok(productService.add(productAddRequestDto));
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> update(@RequestBody ProductUpdateRequestDto productAddRequestDto) {
        return ResponseEntity.ok(productService.update(productAddRequestDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> update(@PathVariable long productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "fromPrice", required = false) Double fromPrice,
            @RequestParam(value = "toPrice", required = false) Double toPrice,
            @RequestParam(value = "rate", required = false) Integer rate) {
        return ResponseEntity.ok(productService.search(name, fromPrice, toPrice, rate));
    }
}
