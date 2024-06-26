package org.example.model;

import org.example.model.product.DiscountDomainModel;

public class DiscountDomainModelTestBuilder {

    private final DiscountDomainModel discount;

    public DiscountDomainModelTestBuilder() {
        discount = new DiscountDomainModel();
    }

    public DiscountDomainModelTestBuilder defaultDiscount() {
        discount.setPriceAfterDiscount(75);
        discount.setDiscountInPercent(25);

        return this;
    }

    public DiscountDomainModelTestBuilder withDiscountInPercent(Integer discountInPercent) {
        discount.setDiscountInPercent(discountInPercent);

        return this;
    }

    public DiscountDomainModel build() {
        return discount;
    }
}