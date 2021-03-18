package shop.application.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.application.dto.request.ProductAddRequestDto;
import shop.application.dto.request.ProductUpdateRequestDto;
import shop.application.dto.response.ProductCategoryResponseDto;
import shop.application.dto.response.ProductCommentResponseDto;
import shop.application.dto.response.ProductRateResponseDto;
import shop.application.dto.response.ProductResponseDto;
import shop.application.exception.ProductNotFoundException;
import shop.application.model.*;
import shop.application.repository.ProductCategoryRepository;
import shop.application.repository.ProductCommentRepository;
import shop.application.repository.ProductRateRepository;
import shop.application.repository.ProductRepository;
import shop.application.service.interfaces.ProductService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCommentRepository productCommentRepository;
    private final ProductRateRepository productRateRepository;
    private final ModelMapper mapper;
    private final ProductCategoryRepository productCategoryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductCommentRepository productCommentRepository,
                              ProductRateRepository productRateRepository,
                              ModelMapper mapper,
                              ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCommentRepository = productCommentRepository;
        this.productRateRepository = productRateRepository;
        this.mapper = mapper;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::buildProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getById(long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return buildProductResponse(product);
    }

    @Override
    public ProductResponseDto add(ProductAddRequestDto productAddRequestDto) {
        Product product = mapper.map(productAddRequestDto, Product.class);
        product.setCreatedDate(LocalDateTime.now());
        List<ProductCategory> productCategoryList = productCategoryRepository.findAllById(productAddRequestDto.getCategoryList());
        product.setProductCategoryList(productCategoryList);
        return buildProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponseDto update(ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = productRepository.findById(productUpdateRequestDto.getId()).orElseThrow(ProductNotFoundException::new);
        mapper.map(productUpdateRequestDto, product);
        List<ProductCategory> productCategoryList = productCategoryRepository.findAllById(productUpdateRequestDto.getCategoryList());
        product.setProductCategoryList(productCategoryList);
        return buildProductResponse(productRepository.save(product));
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> search(String name, Double fromPrice, Double toPrice,
                                           Integer rate) {
        JPQLQuery<?> query = new JPAQuery<>(entityManager);
        QProduct qproduct = QProduct.product;
        QProductRate qproductRate = QProductRate.productRate;
        JPQLQuery<Product> from = query.select(qproduct)
                .from(qproduct);
        BooleanBuilder builder = new BooleanBuilder();
        if (rate != null) {
            from.innerJoin(qproduct.productRateList, qproductRate);
            builder.and(qproduct.id.eq(qproductRate.product.id))
                    .and(qproductRate.rate.eq(rate));
        }
        if (name != null && !name.trim().isEmpty()) {
            builder.and(qproduct.name.like("%" + name + "%"));
        }
        if (fromPrice != null && toPrice != null) {
            builder.and(qproduct.price.between(fromPrice, toPrice));
        }
        from.where(builder);
        return from.fetch()
                .stream()
                .map(this::buildProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponseDto buildProductResponse(Product product) {
        List<ProductCommentResponseDto> productCommentList = productCommentRepository.findAllByProduct(product)
                .stream()
                .map(productComment -> mapper.map(productComment, ProductCommentResponseDto.class))
                .collect(Collectors.toList());
        List<ProductRateResponseDto> productRateList = productRateRepository.findAllByProduct(product)
                .stream()
                .map(productRate -> mapper.map(productRate, ProductRateResponseDto.class))
                .collect(Collectors.toList());
        List<ProductCategoryResponseDto> productCategoryList = product.getProductCategoryList() != null ? product.getProductCategoryList()
                .stream()
                .map(productCategory -> mapper.map(productCategory, ProductCategoryResponseDto.class))
                .collect(Collectors.toList()) : new ArrayList<>();
        ProductResponseDto productResponseDto = mapper.map(product, ProductResponseDto.class);
        productResponseDto.setProductCategoryList(productCategoryList);
        productResponseDto.setProductCommentList(productCommentList);
        productResponseDto.setProductRateList(productRateList);
        return productResponseDto;
    }
}
