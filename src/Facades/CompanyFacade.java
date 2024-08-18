package Facades;

import Utils.Category;
import Beans.Company;
import Beans.Coupon;
import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Customer.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.NotExistException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CompanyFacade extends ClientFacade {
    private int companyId;

    public CompanyFacade() {
    }

    @Override
    public boolean login(String email, String password) throws SQLException, NotExistException {
        ArrayList<Company> companies = companiesDBDAO().getAllCompanies();
        for (Company ref : companies) {
            if (Objects.equals(ref.getEmail(), email) && Objects.equals(ref.getPassword(), password)) {
                this.companyId = ref.getId();
                return true;
            }
        }
        System.out.println("The email or the password are incorrect");
        return false;
    }

    /**
     * Adding coupon to the DB if there is no such Coupon title in this company
     *
     * @param coupon
     * @throws SQLException
     * @throws AlreadyExistException
     */
    public void addCoupon(Coupon coupon) throws SQLException, AlreadyExistException {
        ArrayList<Coupon> coupons = couponDBDAO().getAllCoupons();
        boolean isNotExist = true;
        for (int i = 0; i < coupons.size(); i++) { // valid if the company is already exist
            if (Objects.equals(coupons.get(i).getTitle(), coupon.getTitle()) && coupons.get(i).getCompanyID() == coupon.getCompanyID()) {
                isNotExist = false;
                throw new AlreadyExistException("The coupon is already exist for this company");
            }
        }
        if (isNotExist) {// valid if is a new company
            couponDBDAO().addCoupon(coupon);
        }
    }

    /**
     * update exist coupon by ID,
     * it's impossible to update the Company_id or the Coupon_ID
     *
     * @param coupon
     * @throws SQLException
     * @throws NotExistException
     */
    public void updateCoupon(Coupon coupon) throws SQLException, NotExistException {
        coupon.setCompanyID(couponDBDAO().getOneCoupon(coupon.getId()).getCompanyID());
        couponDBDAO().updateCoupon(coupon);
    }

    public void deleteCoupon(int couponID) throws SQLException {
        couponDBDAO().deleteCouponPurchaseByCouponID(couponID);
        couponDBDAO().deleteCoupon(couponID);
    }

    /**
     *
     * @return all the coupons of this company
     * @throws SQLException
     */
    public ArrayList<Coupon> getCompanyCoupons() throws SQLException {
        ArrayList<Coupon> allCoupons = couponDBDAO().getAllCoupons();
        ArrayList<Coupon> companyCoupons = new ArrayList<>();
        for (Coupon ref : allCoupons) {
            if (ref.getCompanyID() == companyId) {
                companyCoupons.add(ref);
            }
        }
        return companyCoupons;
    }

    /**
     *
     * @param category
     * @return coupons from the type of category you will set.
     * @throws SQLException
     */
    public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException {
        ArrayList<Coupon> allCoupons = couponDBDAO().getAllCoupons();
        ArrayList<Coupon> companyCoupons = new ArrayList<>();
        for (Coupon ref : allCoupons) {
            if (ref.getCompanyID() == companyId && ref.getCategory() == category) {
                companyCoupons.add(ref);
            }
        }
        return companyCoupons;
    }

    /**
     *
     * @param maxPrice
     * @return the company coupons that cost not more than maxPrice
     * @throws SQLException
     */
    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException {
        ArrayList<Coupon> allCoupons = couponDBDAO().getAllCoupons();
        ArrayList<Coupon> selectedCoupons = new ArrayList<>();
        for (Coupon ref : allCoupons) {
            if (ref.getPrice() <= maxPrice) {
                selectedCoupons.add(ref);
            }
        }
        return selectedCoupons;

    }

    public Company getCompanyDetails() throws SQLException {
        return companiesDBDAO().getOneCompany(companyId);
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
