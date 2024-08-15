package Facades;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import DAL.Company.CompaniesDAO;
import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Coupons.CouponsDAO;
import DAL.Customer.CustomerDAO;
import DAL.Customer.CustomerDBDAO;
import Exceptions.AlreadyExistException;
import Exceptions.NotExistException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CompanyFacade extends ClientFacade {
    private int companyId;

    public CompanyFacade(int companyId) {
        this.companyId = companyId;
    }

    public CompanyFacade() {
    }

    @Override
    public boolean login(String email, String password) throws SQLException {
        ArrayList<Company> companies = companiesDBDAO().getAllCompanies();
        for (Company ref : companies) {
            if (Objects.equals(ref.getEmail(), email) && Objects.equals(ref.getPassword(), password)) {
                this.companyId = ref.getId();
                return true;
            }
        }
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

    public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException {
        ArrayList<Coupon> allCoupons = couponDBDAO().getAllCoupons();
        ArrayList<Coupon> companyCoupons = new ArrayList<>();
        for (Coupon ref : allCoupons) {
            System.out.println("the coupon " + ref + "have the category:  " +ref.getCategory());
            if (ref.getCompanyID() == companyId && ref.getCategory() == category) {
                companyCoupons.add(ref);
                System.out.println(companyCoupons);
            }
        }
        return companyCoupons;
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
