import DB.ConnectionPool;
import Exceptions.*;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws SQLException {
        Test test = new Test();
        try {
            test.testAll();
        } catch (NotExistException | AlreadyExistException | CouponException | SQLException | CompanyException | CustomerException e) {
            test.job.stop(); // adding {stop/ interrupt/ close} - here, in case the program shot down in the middle of action
            test.thread.interrupt();
            ConnectionPool.getInstance().closeConnections();
            throw new RuntimeException(e.getMessage());
        }
    }
}
