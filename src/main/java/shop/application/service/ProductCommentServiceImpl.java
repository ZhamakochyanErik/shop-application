package shop.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.application.dto.request.ProductCommentAddRequestDto;
import shop.application.dto.response.ProductCommentResponseDto;
import shop.application.exception.ProductNotFoundException;
import shop.application.model.Product;
import shop.application.model.ProductComment;
import shop.application.model.User;
import shop.application.repository.ProductCommentRepository;
import shop.application.repository.ProductRepository;
import shop.application.service.interfaces.ProductCommentService;

import java.time.LocalDateTime;

@Service
@Transactional
public class ProductCommentServiceImpl implements ProductCommentService {

    private final ProductCommentRepository productCommentRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductCommentServiceImpl(ProductCommentRepository productCommentRepository,
                                     ProductRepository productRepository,
                                     ModelMapper mapper) {
        this.productCommentRepository = productCommentRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductCommentResponseDto add(ProductCommentAddRequestDto productCommentAddRequestDto,
                                         User currentUser) {
        Product product = productRepository.findById(productCommentAddRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        ProductComment productComment = productCommentRepository.save(ProductComment
                .builder()
                .comment(productCommentAddRequestDto.getComment())
                .createdDate(LocalDateTime.now())
                .product(product)
                .user(currentUser)
                .build());
        return mapper.map(productComment, ProductCommentResponseDto.class);
    }
}
