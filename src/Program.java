import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.NotExistException;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
        Test test = new Test();
        try {
            test.testAll();
        } catch (NotExistException | AlreadyExistException | CouponException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
