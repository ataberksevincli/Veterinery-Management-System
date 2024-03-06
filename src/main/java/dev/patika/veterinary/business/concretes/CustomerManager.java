package dev.patika.veterinary.business.concretes;

import dev.patika.veterinary.business.abstracts.ICustomerService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.exception.NotFoundException;
import dev.patika.veterinary.core.utilies.Msg;
import dev.patika.veterinary.dao.CustomerRepository;
import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepository customerRepository;
    private IModelMapperService modelMapper;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer get(int id) {
        return this.customerRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepository.save(customer);
    }

    @Override
    public boolean delete(int id) {
        Customer customer = this.get(id);
        this.customerRepository.delete(customer);
        return true;
    }

    // Finds and returns customers with names that contain the given substring, case-insensitive
    @Override
    public List<CustomerResponse> findCustomersByName(String name) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);
        return customers.stream()
                .map(customer -> modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }
}
