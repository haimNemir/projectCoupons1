package Facades;

import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Customer.CustomerDBDAO;
import Exceptions.NotExistException;

import java.sql.SQLException;

public abstract class ClientFacade {
    public abstract boolean login(String email, String password) throws SQLException, NotExistException;

    protected abstract CompaniesDBDAO companiesDBDAO() throws SQLException;

    protected abstract CustomerDBDAO customerDBDAO() throws SQLException;

    protected abstract CouponDBDAO couponDBDAO() throws SQLException;
}
