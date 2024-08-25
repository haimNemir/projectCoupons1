package DAL.Customer;

import Beans.Customer;
import DB.ConnectionPool;
import Exceptions.CouponException;
import Exceptions.CustomerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDBDAO implements CustomerDAO{
    private ConnectionPool pool = ConnectionPool.getInstance();

    public CustomerDBDAO() throws SQLException {
    }
    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.customers where email = (?) and password = (?);");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            return (rs.next());
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, CustomerException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("insert into couponsdb.customers(first_name, last_name, email, password) values(?, ?, ?, ?);");
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            if (statement.executeUpdate() <= 0) {
                pool.restoreConnection(con);
                throw new CustomerException("Not found any customer to add");
            }
        } finally {
            pool.restoreConnection(con);
        }
    }
    /** This method updates existing Customers by getting the following:
     *  customer ID, first name, last name, email, password.
     *  but the ID is not updated.
     */
    @Override
    public void updateCustomer(Customer customer) throws SQLException, CustomerException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("update couponsdb.customers set first_name = (?), last_name = (?), email = (?), password = (?) where id = (?);");
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            statement.setInt(5, customer.getId());
            if (statement.executeUpdate() <= 0) {
                pool.restoreConnection(con);
                throw new CustomerException("Not found any customer to update");
            }
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void deleteCustomer(int customerID) throws SQLException, CustomerException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("delete from couponsdb.customers where id = (?);");
            statement.setInt(1, customerID);
            if (statement.executeUpdate() <= 0) {
                pool.restoreConnection(con);
                throw new CustomerException("Not found any customer to delete");
            }
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.customers");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        null));
            }
        } finally {
            pool.restoreConnection(con);
        }
        return customers;
    }

    @Override
    public Customer getOneCustomer(int customerID) throws SQLException {
        Customer customer = null;
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.customers where id = (?)");
            statement.setInt(1, customerID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customer = new Customer (
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        null);
            }
        } finally {
            pool.restoreConnection(con);
        }
        return customer;
    }
}
