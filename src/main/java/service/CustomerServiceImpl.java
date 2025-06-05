package service;

import model.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {



    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customers");
        while (resultSet.next())
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        return customerList;
    }

    //

    @Override
    public Boolean addCustomer(Customer customer) {
        return null;
    }

    @Override
    public Boolean updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Boolean deleteCustomer(String id) {
        return null;
    }

    @Override
    public Customer searchById(String id) {
        return null;
    }
}
