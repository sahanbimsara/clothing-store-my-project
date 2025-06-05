package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {

    List<Customer> getAll() throws SQLException;

    Boolean addCustomer(Customer customer);

    Boolean updateCustomer(Customer customer);

    Boolean deleteCustomer(String id);

    Customer searchById(String id);
}
