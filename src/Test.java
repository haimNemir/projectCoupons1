import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DB.ConnectionPool;
import Exceptions.AlreadyExistException;
import Exceptions.CouponException;
import Exceptions.NotExistException;
import Facades.*;
import Utils.*;

import java.sql.SQLException;

public class Test {
    public void testAll() throws SQLException, NotExistException, AlreadyExistException, CouponException {
        CouponExpirationDailyJob job = new CouponExpirationDailyJob();
        Thread thread = new Thread(job);
        thread.start(); // start thread Job!!!

        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Administrator);
        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("google@gmail.com", "14327842", ClientType.Company);
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("haim@gmail.com", "1234", ClientType.Customer);


        // Admin facade methods:

        Company company = adminFacade.getOneCompany(1);
        System.out.println(adminFacade.getOneCompany(1));
        company.setEmail("tesla@gmail.com");
        adminFacade.updateCompany(company);
        boolean isNotExist = true;
        while (isNotExist) {
            String nameCompany = RandomCompanyName.getRandomName();
            try {
                adminFacade.addCompany(new Company(nameCompany, nameCompany + "@walla.il", RandomPassword.getRandomPassword(), null));
                isNotExist = false;
                System.out.println("Company added successfully!");
            } catch (AlreadyExistException e) {
                isNotExist = true;
            }
        }
        adminFacade.deleteCompany(16);
        System.out.println(adminFacade.getAllCompanies());
        String firstName = RandomFirstName.getRandomName();
        Customer customer = new Customer(firstName, RandomLastName.getRandomName(), "rom" + "@gamil.com", RandomPassword.getRandomPassword(), null);
        adminFacade.addCustomer(customer);
        Customer customer1 = adminFacade.getOneCustomer(3);
        customer1.setEmail("yakov@gmail.com");
        adminFacade.updateCustomer(customer1);
        adminFacade.deleteCustomer(8);
        System.out.println(adminFacade.getAllCustomers());
        System.out.println(adminFacade.getOneCustomer(1));


//        // Company facade methods:

        Coupon coupon = new Coupon(1, Category.FASHION, "suit up!", "Receiving a set of suits!", CreateDate.getDate(2020, 8, 17), CreateDate.getDate(2023, 8, 17), 35, 66.3, null);

        companyFacade.deleteCoupon(26);
        companyFacade.addCoupon(coupon);
        Coupon coupon1 = companyFacade.getCompanyCoupons().get(0);
        coupon1.setTitle("suit up!");
        companyFacade.updateCoupon(coupon1);
        System.out.println(companyFacade.getCompanyCoupons());
        System.out.println(companyFacade.getCompanyCoupons(Category.ELECTRICITY));
        System.out.println(companyFacade.getCompanyCoupons(50));
        System.out.println(companyFacade.getCompanyDetails());


//        // Customer facade methods:

        Coupon coupon2 = companyFacade.getCompanyCoupons().get(0);
        customerFacade.purchaseCoupon(coupon2);
        System.out.println(customerFacade.getCustomerCoupons());
        System.out.println(customerFacade.getCustomerCoupons(Category.CINEMA));
        System.out.println(customerFacade.getCustomerDetails());
        System.out.println(customerFacade.getCustomerCoupons(50));

        job.stop();
        thread.interrupt(); // גורם ל-thread לזרוק InterruptedException אם הוא במצב שינה
        ConnectionPool.getInstance().closeConnections();

    }
}