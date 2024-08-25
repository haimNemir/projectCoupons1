import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DAL.Company.CompaniesDBDAO;
import DB.ConnectionPool;
import Exceptions.*;
import Facades.*;
import Utils.*;

import java.sql.Date;
import java.sql.SQLException;

public class Test {
    public CouponExpirationDailyJob job = new CouponExpirationDailyJob();
    public Thread thread = new Thread(job);

    public Test() throws SQLException {
    }

    public void testAll() throws SQLException, NotExistException, AlreadyExistException, CouponException, CompanyException, CustomerException {

        thread.start(); // start thread Job!!!

        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.Administrator);
        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("google@gmail.com", "4433228811", ClientType.Company);
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("haim@gmail.com", "1234", ClientType.Customer);


        // Admin facade methods:
        //Add Company:
        adminFacade.addCompany(new Company("johnCoupon", "johnCoupon" + "@walla.il", "ch7qjcu2", null));

        //Update Company + Get one Company:
        Company company = adminFacade.getOneCompany(1);
        company.setEmail("tesla@gmail.com");
        adminFacade.updateCompany(company);

        //Delete Company:
        adminFacade.deleteCompany(16);

        //Get All Companies:
        System.out.println(adminFacade.getAllCompanies());

        //Add Customer:
        adminFacade.addCustomer(new Customer("Yohav", "Mehir", "YohavMehir" + "@gmail.com", "j75sbc5a0", null));

        //Get one Customer + Update Customer:
        Customer customer = adminFacade.getOneCustomer(3);
        customer.setEmail("DanRotem@gmail.com");
        adminFacade.updateCustomer(customer);

        //Delete Customer:
        adminFacade.deleteCustomer(8);

        // Get one Customer + Get all Customers:
        System.out.println(adminFacade.getAllCustomers());
        System.out.println(adminFacade.getOneCustomer(1));


        //Company Facade methods:
        // Add Coupon:
        companyFacade.addCoupon(new Coupon(16, Category.ELECTRICITY, "laptop", "get 50% off on lepton", Date.valueOf("2022-09-09"), Date.valueOf("2025-09-09"), 99, 166.5, null));

        //Update Coupon:
        Coupon coupon = companyFacade.getCompanyCoupons().get(1);
        coupon.setAmount(34);
        companyFacade.updateCoupon(coupon);

        // Delete Coupon:
        companyFacade.deleteCoupon(29);

        // Get Company Coupon:
        System.out.println(companyFacade.getCompanyCoupons());

        System.out.println(companyFacade.getCompanyCoupons(Category.CINEMA));

        System.out.println(companyFacade.getCompanyCoupons(50));

        // Get Company details:
        System.out.println(companyFacade.getCompanyDetails());


        //Customer Facade methods:
        // Purchase Coupon:
        customerFacade.purchaseCoupon(companyFacade.getCompanyCoupons().getFirst());

        // Get Customer Coupons:
        System.out.println(customerFacade.getCustomerCoupons());

        System.out.println(customerFacade.getCustomerCoupons(Category.CINEMA));

        System.out.println(customerFacade.getCustomerCoupons(50));

        // Get Customer details:
        System.out.println(customerFacade.getCustomerDetails());

        job.stop();
        thread.interrupt(); // גורם ל-thread לזרוק InterruptedException אם הוא במצב שינה כי אם לא הוא יחכה 24 שעות.
        ConnectionPool.getInstance().closeConnections();
    }
}