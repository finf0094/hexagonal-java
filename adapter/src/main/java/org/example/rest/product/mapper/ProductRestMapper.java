package org.example.rest.product.mapper;

import org.example.model.product.ProductDomainModel;
import org.example.rest.product.dto.ProductDetailsDto;
import org.example.rest.product.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductRestMapper {
    ProductRestMapper PRODUCT_REST_MAPPER = getMapper(ProductRestMapper.class);

    @Mapping(target = "availableAmount", source = "availableInWarehouse")
    @Mapping(target = "discount.priceAfterDiscount", source = "discount.priceAfterDiscount")
    @Mapping(target = "discount.discountInPercent", source = "discount.discountInPercent")
    ProductDetailsDto toProductDetailsDto(ProductDomainModel productDomainModel);

    @Mapping(target = "discount.priceAfterDiscount", source = "discount.priceAfterDiscount")
    @Mapping(target = "discount.discountInPercent", source = "discount.discountInPercent")
    ProductDto toDto(ProductDomainModel productDomainModel);

    List<ProductDto> toDtoList(List<ProductDomainModel> productDomainModelList);
}