package DAL;

import DAL.Customer.CustomerDBDAO;
import Utils.RandomFirstName;
import Utils.RandomLastName;
import Utils.RandomPassword;
import beans.Customer;

import java.sql.SQLException;
import java.util.TreeMap;

public class test {
    public static void main(String[] args) {
        try {
            CustomerDBDAO customer1 = new CustomerDBDAO();
            String firstName = RandomFirstName.getRandomName();
            String lastName = RandomLastName.getRandomName();
            System.out.println(customer1.getOneCustomer(3));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




//        printN(10);
    }
     public static void printN(int number){
        if (number > 0 ){
            System.out.println(number);
            printN(number-1);
        }
    }
}