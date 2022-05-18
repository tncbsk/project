package com.getir.readingisgood.mapper;

import com.getir.readingisgood.entity.Customer;
import com.getir.readingisgood.input.CustomerInput;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerInput convertToCustomerDto(Customer customer);

    Customer convertToCustomer(CustomerInput customer);

}
