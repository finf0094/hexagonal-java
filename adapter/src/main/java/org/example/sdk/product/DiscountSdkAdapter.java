package org.example.sdk.product;


import lombok.RequiredArgsConstructor;
import org.example.model.product.DiscountDomainModel;
import org.example.model.product.ProductDomainModel;
import org.example.port.spi.product.DiscountSdkPort;
import org.example.sdk.SdkConfig;
import org.example.sdk.SdkConfig.DiscountService;
import org.springframework.stereotype.Component;

import static org.example.sdk.product.mapper.DiscountSdkMapper.DISCOUNT_SDK_MAPPER;

@Component
@RequiredArgsConstructor
public class DiscountSdkAdapter implements DiscountSdkPort {

    private final DiscountService discountService;

    @Override
    public DiscountDomainModel getDiscount(ProductDomainModel productDomainModel) {
        var discount = discountService.getProductDiscount(productDomainModel.getId());

        return DISCOUNT_SDK_MAPPER.toDomainModel(discount);
    }
}
