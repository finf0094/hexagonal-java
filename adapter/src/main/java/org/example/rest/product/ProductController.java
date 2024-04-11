package org.example.rest.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.port.api.product.ProductServicePort;
import org.example.rest.product.dto.ProductDetailsDto;
import org.example.rest.product.dto.ProductDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.rest.product.mapper.ProductRestMapper.PRODUCT_REST_MAPPER;

@Tag(name = "product", description = "Product operations")
@RestController
@RequestMapping("products")
@Validated
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort productServicePort;

    @Operation(summary = "Get all available products")
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return PRODUCT_REST_MAPPER.toDtoList(productServicePort.getAllProducts());
    }

    @Operation(summary = "Find the product details by id")
    @GetMapping("{id}")
    public ProductDetailsDto getProductDetails(@Parameter(description = "Id of the product") @PathVariable @NotNull @Positive Integer id) {
        return PRODUCT_REST_MAPPER.toProductDetailsDto(productServicePort.getProductById(id));
    }
}