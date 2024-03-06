package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.entity.AvailableDate;
import dev.patika.veterinary.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer );
    Customer get(int id);
    Page<Customer> cursor(int page, int pageSize);
    Customer update(Customer customer);
    boolean delete(int id);
    List<CustomerResponse> findCustomersByName(String name);

}
