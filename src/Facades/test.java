package Facades;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Customer.CustomerDBDAO;
import DB.ConnectionPool;
import Exceptions.AlreadyExistException;
import Exceptions.NotExistException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws SQLException, ParseException, NotExistException, AlreadyExistException {
        CompanyFacade companyFacade = new CompanyFacade(5);
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        CouponDBDAO couponDBDAO = new CouponDBDAO();
        companyFacade.login("tesla@gmail.com", "112233");

        companyFacade.getCompanyCoupons(Category.CINEMA); // gets the wrong value from beans/category

    }

}