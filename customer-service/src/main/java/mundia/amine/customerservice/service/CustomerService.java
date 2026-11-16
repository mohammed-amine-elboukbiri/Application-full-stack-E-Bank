package mundia.amine.customerservice.service;

import mundia.amine.customerservice.entities.Customer;
import mundia.amine.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new RuntimeException("there is no customer with this id"));
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }



}
