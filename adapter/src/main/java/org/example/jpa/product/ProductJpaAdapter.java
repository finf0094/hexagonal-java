package org.example.jpa.product;

import lombok.RequiredArgsConstructor;
import org.example.jpa.product.repository.ProductRepository;
import org.example.model.product.ProductDomainModel;
import org.example.port.spi.product.ProductJpaPort;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.example.jpa.product.mapper.ProductJpaMapper.PRODUCT_JPA_MAPPER;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductJpaPort {

    private final ProductRepository productRepository;

    @Override
    public ProductDomainModel getProductById(Integer id) {
        return PRODUCT_JPA_MAPPER.toDomainModel(productRepository.findById(id).orElse(null));
    }

    @Override
    public List<ProductDomainModel> getAllProductByIds(Collection<Integer> productIds) {
        return PRODUCT_JPA_MAPPER.toDomainModelList(productRepository.findAllById(productIds));
    }
}
