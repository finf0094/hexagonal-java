package org.example.jpa.order.mapper;

import org.example.jpa.order.entity.OrderEntity;
import org.example.model.order.OrderDomainModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(imports = LocalDateTime.class)
public interface OrderJpaMapper {

    OrderJpaMapper ORDER_JPA_MAPPER = getMapper(OrderJpaMapper.class);

    @Mapping(target = "creationDate", expression = "java(LocalDateTime.now())")
    OrderEntity toEntity(OrderDomainModel orderDomainModel);

    @Mapping(target = "product.name", ignore = true)
    @Mapping(target = "product.description", ignore = true)
    @Mapping(target = "orderId", source = "id")
    OrderDomainModel toDomainModel(OrderEntity orderEntity);

    List<OrderDomainModel> toDomainModelList(List<OrderEntity> orderDomainModelList);
}