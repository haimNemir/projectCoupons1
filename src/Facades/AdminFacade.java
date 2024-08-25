package Facades;

import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Customer.CustomerDBDAO;
import Exceptions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Give access for the Administrator to the DataBase .
 */
public class AdminFacade extends ClientFacade {
    public AdminFacade() {
    }

    @Override
    public boolean login(String email, String password) {
        return Objects.equals(email, "admin@admin.com") && Objects.equals(password, "admin");
    }

    /**
     * Add Company to the DB if there is no such Company with this name or email.
     */
    public void addCompany(Company company) throws SQLException, AlreadyExistException, CompanyException {
        ArrayList<Company> companies = companiesDBDAO().getAllCompanies();
        for (Company value : companies) { // valid if the company is already exist
            if (Objects.equals(company.getName(), value.getName()) || Objects.equals(company.getEmail(), value.getEmail())) {
                throw new AlreadyExistException("The company is already exist by name or email!!!");
            }
        }
        companiesDBDAO().addCompany(company);
    }

    /**
     * @param company get Company with ID, and update the same Company in the DB by the values in the Company.
     *                This method will not update the ID and the name of the Company.
     * @throws NotExistException if there is no Company with this ID it will throw Exception.
     * @throws SQLException
     */
    public void updateCompany(Company company) throws NotExistException, SQLException, CompanyException {
        if (companiesDBDAO().getOneCompany(company.getId()) != null) {
            companiesDBDAO().updateCompany(company);
        } else {
            throw new NotExistException("There is no Company with this ID");
        }
    }

    /**
     * delete Company by ID.
     * For delete the Company it will delete also the Coupons of
     * the company(from DB table coupons) and also the purchased coupons of the customers
     *
     * @param companyID
     * @throws SQLException
     */
    public void deleteCompany(int companyID) throws SQLException, CompanyException, CouponException {
        ArrayList<Coupon> allCouponsOfThisCompany = couponDBDAO().getAllCoupons();
        ArrayList<Integer> couponsOfThisCompany = new ArrayList<>();
        for (int i = 0; i < allCouponsOfThisCompany.size(); i++) { // get the Coupons(!) ID of the company.
            if (allCouponsOfThisCompany.get(i).getCompanyID() == companyID) {// check if Company_id inside Coupon equal to the companyID that we got.
                couponsOfThisCompany.add(allCouponsOfThisCompany.get(i).getId());
            }
        }
        for (int i = 0; i < couponsOfThisCompany.size(); i++) {
            couponDBDAO().deleteCouponPurchaseByCouponID(couponsOfThisCompany.get(i)); // Delete the right Coupons of the Company from DB Table - customers_vs_coupons.
        }
        for (int i = 0; i < couponsOfThisCompany.size(); i++) {
            couponDBDAO().deleteCoupon(couponsOfThisCompany.get(i));//Delete the right Coupon from DB table - coupons.
        }
        companiesDBDAO().deleteCompany(companyID);
    }

    public ArrayList<Company> getAllCompanies() throws SQLException {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        return companiesDBDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyID) throws SQLException {
        return companiesDBDAO().getOneCompany(companyID);
    }

    /**
     * add new customer to the DB if there is no such customer with this email there
     *
     * @param customer
     * @throws SQLException
     */
    public void addCustomer(Customer customer) throws SQLException, AlreadyExistException, CustomerException {//!!!!!!!!!!!!!!check this method
        ArrayList<Customer> allCustomers = customerDBDAO().getAllCustomers();
        for (Customer ref : allCustomers) {
            if (Objects.equals(ref.getEmail(), customer.getEmail())) {
                throw new AlreadyExistException("The customer Already exist!");
            }
        }
        customerDBDAO().addCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException, CustomerException {
        customerDBDAO().updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException, CouponException, CustomerException {
        couponDBDAO().deleteCouponPurchaseByCustomerID(customerID);
        customerDBDAO().deleteCustomer(customerID);
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        return customerDBDAO().getAllCustomers();
    }

    public Customer getOneCustomer(int customerID) throws SQLException {
        return customerDBDAO().getOneCustomer(customerID);
    }

    @Override
    protected CompaniesDBDAO companiesDBDAO() throws SQLException {
        return new CompaniesDBDAO();
    }

    @Override
    protected CustomerDBDAO customerDBDAO() throws SQLException {
        return new CustomerDBDAO();
    }

    @Override
    protected CouponDBDAO couponDBDAO() throws SQLException {
        return new CouponDBDAO();
    }


}
