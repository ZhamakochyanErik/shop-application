package shop.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "productCategory not found")
public class ProductCategoryNotFoundException extends RuntimeException {
}
