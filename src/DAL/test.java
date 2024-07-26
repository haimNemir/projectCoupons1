package DAL;

import DAL.Customer.CustomerDBDAO;
import Utils.RandomFirstName;
import Utils.RandomLastName;
import Utils.RandomPassword;
import beans.Customer;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        try {
            CustomerDBDAO customer1 = new CustomerDBDAO();
            String firstName = RandomFirstName.getRandomName();
            String lastName = RandomLastName.getRandomName();

            customer1.addCustomer(new Customer(firstName, RandomLastName.getRandomName(), firstName + "@gmail.com", RandomPassword.getRandomPassword(), null));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}