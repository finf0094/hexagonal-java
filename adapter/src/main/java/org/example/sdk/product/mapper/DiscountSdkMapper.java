package org.example.sdk.product.mapper;

import org.example.model.product.DiscountDomainModel;
import org.example.sdk.SdkConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface DiscountSdkMapper {

    DiscountSdkMapper DISCOUNT_SDK_MAPPER = getMapper(DiscountSdkMapper.class);

    @Mapping(target = "discountInPercent", source = "discount")
    DiscountDomainModel toDomainModel(SdkConfig.Discount discount);
}