package shop.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.application.dto.request.ProductCategoryAddRequestDto;
import shop.application.dto.request.ProductCategoryUpdateRequestDto;
import shop.application.dto.response.ProductCategoryResponseDto;
import shop.application.exception.ProductCategoryNotFoundException;
import shop.application.model.ProductCategory;
import shop.application.repository.ProductCategoryRepository;
import shop.application.service.interfaces.ProductCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ModelMapper mapper;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository,
                                      ModelMapper mapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductCategoryResponseDto> getAll() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategory -> mapper.map(productCategory, ProductCategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryResponseDto add(ProductCategoryAddRequestDto productCategoryAddRequestDto) {
        ProductCategory productCategory = mapper.map(productCategoryAddRequestDto, ProductCategory.class);
        return mapper.map(productCategoryRepository.save(productCategory), ProductCategoryResponseDto.class);
    }

    @Override
    public ProductCategoryResponseDto update(ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto) {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryUpdateRequestDto.getId()).orElseThrow(ProductCategoryNotFoundException::new);
        mapper.map(productCategoryUpdateRequestDto, productCategory);
        return mapper.map(productCategoryRepository.save(productCategory), ProductCategoryResponseDto.class);
    }

    @Override
    public void delete(long id) {
        productCategoryRepository.deleteById(id);
    }
}
