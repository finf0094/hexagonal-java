package org.example.service.product;

import lombok.RequiredArgsConstructor;
import org.example.model.product.ProductDomainModel;
import org.example.port.api.product.ProductServicePort;
import org.example.port.spi.product.DiscountSdkPort;
import org.example.port.spi.product.ProductJpaPort;
import org.example.port.spi.product.ProductS3Port;
import org.example.port.spi.product.WarehouseSdkPort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.service.product.mapper.ProductApplicationServiceMapper.PRODUCT_APPLICATION_SERVICE_MAPPER;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductServicePort {

    private final ProductS3Port productS3Port;
    private final ProductJpaPort productJpaPort;
    private final DiscountSdkPort discountSdkPort;
    private final WarehouseSdkPort warehouseSdkPort;

    @Override
    public List<ProductDomainModel> getAllProducts() {
        var availableProductsMap = warehouseSdkPort.getAvailableProducts()
                .stream()
                .collect(toMap(ProductDomainModel::getId, identity()));

        return productJpaPort.getAllProductByIds(availableProductsMap.values().stream().map(ProductDomainModel::getId).toList())
                .stream()
                .map(jpaProduct -> {
                    var mainPicture = productS3Port.getMainPicture(jpaProduct);
                    var availableInWarehouse = availableProductsMap.get(jpaProduct.getId()).getAvailableInWarehouse();
                    var discount = discountSdkPort.getDiscount(jpaProduct);

                    jpaProduct.applyDiscount(discount);

                    return PRODUCT_APPLICATION_SERVICE_MAPPER.toDomainModel(jpaProduct, mainPicture, availableInWarehouse);
                })
                .toList();
    }

    @Override
    public ProductDomainModel getProductById(Integer id) {
        var jpaProduct = productJpaPort.getProductById(id);
        var s3Product = productS3Port.getPictures(jpaProduct);
        var discount = discountSdkPort.getDiscount(jpaProduct);
        var warehouseProduct = warehouseSdkPort.getAvailableAmount(jpaProduct);

        jpaProduct.applyDiscount(discount);

        return PRODUCT_APPLICATION_SERVICE_MAPPER.toDomainModel(jpaProduct, s3Product, warehouseProduct);
    }

    @Override
    public void removeFromWarehouse(Integer amount, ProductDomainModel product) {
        warehouseSdkPort.remove(amount, product);
    }
}