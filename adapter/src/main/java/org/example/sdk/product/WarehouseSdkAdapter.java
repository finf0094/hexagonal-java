package org.example.sdk.product;

import lombok.RequiredArgsConstructor;
import org.example.model.product.ProductDomainModel;
import org.example.port.spi.product.WarehouseSdkPort;
import org.example.sdk.SdkConfig;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.sdk.SdkConfig.*;
import static org.example.sdk.product.mapper.WarehouseSdkMapper.WAREHOUSE_SDK_MAPPER;

@Component
@RequiredArgsConstructor
public class WarehouseSdkAdapter implements WarehouseSdkPort {

    private final WarehouseService warehouseService;

    @Override
    public ProductDomainModel getAvailableAmount(ProductDomainModel productDomainModel) {
        return WAREHOUSE_SDK_MAPPER.toDomainModel(warehouseService.getProductAvailability(productDomainModel.getId()));
    }

    @Override
    public List<ProductDomainModel> getAvailableProducts() {
        return WAREHOUSE_SDK_MAPPER.toDomainModelList(warehouseService.getAllAvailableProducts());
    }

    @Override
    public void remove(Integer amount, ProductDomainModel product) {
        warehouseService.remove(amount, product);
    }
}