package Facades;

import Beans.Customer;
import DAL.Customer.CustomerDBDAO;

import java.sql.SQLException;
import java.text.ParseException;

public class test {
    public static void main(String[] args) throws SQLException, ParseException {
        AdminFacade adminFacade = new AdminFacade();

        CustomerDBDAO customerDBDAO = new CustomerDBDAO();

        adminFacade.deleteCustomer(6);
    }
}