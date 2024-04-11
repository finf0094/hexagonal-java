package org.example.port.spi.product;

import org.example.model.product.DiscountDomainModel;
import org.example.model.product.ProductDomainModel;

/**
 * Secondary port is used to manage discounts on SDK level
 */
public interface DiscountSdkPort {

    /**
     * Gets a discount for the given product.
     * Discount is in the percent form, so the actual sum has to be calculated
     *
     * @param productDomainModel product which discount we are looking for
     * @return discount object
     */
    DiscountDomainModel getDiscount(ProductDomainModel productDomainModel);
}