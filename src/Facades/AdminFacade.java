package Facades;

import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Customer.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.NotExistException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Give access for the Administrator to the DataBase .
 */
public class AdminFacade extends ClientFacade {

    @Override
    public boolean login(String email, String password) {
        return Objects.equals(email, "admin@admin.com") && Objects.equals(password, "admin");
    }

    /**
     * Add Company to the DB if there is no such Company in there.
     *
     * @param company
     * @throws SQLException
     * @throws AlreadyExistException
     */
    public void addCompany(Company company) throws SQLException, AlreadyExistException {
        ArrayList<Company> companies = companiesDBDAO().getAllCompanies();
        boolean isNotExist = true;
        for (int i = 0; i < companies.size(); i++) { // valid if the company is already exist
            if (Objects.equals(company.getName(), companies.get(i).getName()) || Objects.equals(company.getEmail(), companies.get(i).getEmail())) {
                isNotExist = false;
                throw new AlreadyExistException("The company is already exist by name or email!!!");
            }
        }
        if (isNotExist) {// valid if is a new company
            companiesDBDAO().addCompany(company);
        }
    }

    /**
     * @param company get Company with ID, and update the same Company in the DB by the values in the Company.
     *                This method will not update the ID and the name of the Company.
     *                if there is no Company with this ID its throw Exception.
     * @throws NotExistException
     * @throws SQLException
     */
    public void updateCompany(Company company) throws NotExistException, SQLException {
        ArrayList<Company> companies = companiesDBDAO().getAllCompanies();
        boolean isExist = false;
        for (int i = 0; i < companies.size(); i++) { // valid if the company is already exist
            if (company.getId() == companies.get(i).getId()) {
                companiesDBDAO().updateCompany(company);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            throw new NotExistException("There is no Company with this ID");
        }
    }

    /**
     * delete Company by ID.
     * For delete the Company it will delete also the Coupons of the company(from DB table coupons) and also the purchased coupons of the customers
     *
     * @param companyID
     * @throws SQLException
     */
    public void deleteCompany(int companyID) throws SQLException {
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
        ArrayList<Coupon> allCoupons = couponDBDAO().getAllCoupons();
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
     * @param customer
     * @throws SQLException
     */
    public void addCustomer(Customer customer) throws SQLException, AlreadyExistException {
        ArrayList<Customer> allCustomers = customerDBDAO().getAllCustomers();
        boolean thisCustomerExist = false;
        for (Customer ref : allCustomers) {
            if (Objects.equals(ref.getEmail(), customer.getEmail())) {
                thisCustomerExist = true;
                break;
            }
        }
        if (!thisCustomerExist) {
            customerDBDAO().addCustomer(customer);
        } else {
            throw new AlreadyExistException("The customer Already exist!");
        }
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDBDAO().updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException {
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
