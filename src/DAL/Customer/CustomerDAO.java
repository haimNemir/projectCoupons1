package DAL.Customer;

import Beans.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public  interface CustomerDAO {
    public boolean isCustomerExists(String email, String password) throws SQLException;
    public void addCustomer(Customer customer) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerID) throws SQLException;
    public ArrayList<Customer> getAllCustomers() throws SQLException;
    public Customer getOneCustomer(int customerID) throws SQLException;
}
