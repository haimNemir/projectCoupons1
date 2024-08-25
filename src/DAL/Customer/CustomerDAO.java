package DAL.Customer;

import Beans.Customer;
import Exceptions.CustomerException;

import java.sql.SQLException;
import java.util.ArrayList;

public  interface CustomerDAO {
    public boolean isCustomerExists(String email, String password) throws SQLException;
    public void addCustomer(Customer customer) throws SQLException, CustomerException;
    public void updateCustomer(Customer customer) throws SQLException, CustomerException;
    public void deleteCustomer(int customerID) throws SQLException, CustomerException;
    public ArrayList<Customer> getAllCustomers() throws SQLException;
    public Customer getOneCustomer(int customerID) throws SQLException;
}
