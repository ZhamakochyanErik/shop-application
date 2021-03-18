package shop.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.application.dto.request.ProductRateAddRequestDto;
import shop.application.dto.response.ProductRateResponseDto;
import shop.application.exception.ProductNotFoundException;
import shop.application.model.Product;
import shop.application.model.ProductRate;
import shop.application.model.User;
import shop.application.repository.ProductRateRepository;
import shop.application.repository.ProductRepository;
import shop.application.service.interfaces.ProductRateService;

import java.time.LocalDateTime;

@Service
@Transactional
public class ProductRateServiceImpl implements ProductRateService {

    private final ProductRateRepository productRateRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductRateServiceImpl(ProductRateRepository productRateRepository,
                                  ProductRepository productRepository,
                                  ModelMapper mapper) {
        this.productRateRepository = productRateRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductRateResponseDto add(ProductRateAddRequestDto productRateAddRequestDto, User currentUser) {
        Product product = productRepository.findById(productRateAddRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        ProductRate productRate = productRateRepository.save(ProductRate
                .builder()
                .createdDate(LocalDateTime.now())
                .product(product)
                .rate(productRateAddRequestDto.getRate())
                .user(currentUser)
                .build());
        return mapper.map(productRate,ProductRateResponseDto.class);
    }
}
