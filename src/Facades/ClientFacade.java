package Facades;

import Beans.Customer;
import DAL.Company.CompaniesDAO;
import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Coupons.CouponsDAO;
import DAL.Customer.CustomerDAO;
import DAL.Customer.CustomerDBDAO;

import java.sql.SQLException;

public abstract class ClientFacade {
    public abstract boolean login(String email, String password);

    protected abstract CompaniesDBDAO companiesDBDAO() throws SQLException;

    protected abstract CustomerDBDAO customerDBDAO() throws SQLException;

    protected abstract CouponDBDAO couponDBDAO() throws SQLException;
}
