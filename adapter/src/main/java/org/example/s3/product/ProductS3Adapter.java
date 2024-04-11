package org.example.s3.product;

import org.example.model.product.ProductDomainModel;
import org.example.s3.S3Config.S3Client;
import lombok.RequiredArgsConstructor;
import org.example.port.spi.product.ProductS3Port;
import org.example.s3.S3Config;
import org.springframework.stereotype.Component;

import static org.example.s3.product.mapper.ProductS3Mapper.PRODUCT_S3_MAPPER;

@Component
@RequiredArgsConstructor
public class ProductS3Adapter implements ProductS3Port {

    public static final String PICTURE_GALLERY = "pictureGallery/";

    private final S3Client s3Client;

    @Override
    public ProductDomainModel getPictures(ProductDomainModel productDomainModel) {
        var mainPicture = getMainPicture(productDomainModel);
        var pictureGallery = s3Client.getObjects(PICTURE_GALLERY + productDomainModel.getId());

        return PRODUCT_S3_MAPPER.toDomainModel(mainPicture, pictureGallery);
    }

    @Override
    public byte[] getMainPicture(ProductDomainModel productDomainModel) {
        return s3Client.getObject(productDomainModel.getId().toString());
    }
}