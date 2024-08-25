package Facades;

import Utils.Category;
import Beans.Coupon;
import Beans.Customer;
import DAL.Company.CompaniesDBDAO;
import DAL.Coupons.CouponDBDAO;
import DAL.Customer.CustomerDBDAO;
import Exceptions.CouponException;
import Exceptions.NotExistException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class CustomerFacade extends ClientFacade {
    private int customerId;

    public CustomerFacade() {
    }

    @Override
    public boolean login(String email, String password) throws SQLException, NotExistException {
        ArrayList<Customer> customers = customerDBDAO().getAllCustomers();
        for (Customer ref : customers) {
            if (Objects.equals(ref.getEmail(), email) && Objects.equals(ref.getPassword(), password)) {
                this.customerId = ref.getId();
                return true;
            }
        }
        System.out.println("The email or the password are incorrect");
        return false;
    }

    /**
     *
     * @param coupon adding purchased coupon to the table customers_vs_coupons.
     *               if this coupon already purchased to this customer,
     *               or if the coupon expiration date left,
     *               or if the coupon already sold out,
     *               or if this coupon does not exist = it will not purchase the coupon.
     *
     * @throws SQLException
     * @throws CouponException
     * @throws NotExistException
     */
    public void purchaseCoupon(Coupon coupon) throws SQLException, CouponException, NotExistException {
        Coupon couponFromDB = couponDBDAO().getOneCoupon(coupon.getId());
        if (couponFromDB == null ) { // check if this coupon exist in the DB
            System.out.println("this coupon does not exist!");
            return;
        }
        Date currentDate = new Date();
        if (currentDate.after(coupon.getEndDate())) {//check if the expiration date if left
            System.out.println("The expiration date is left!");
            return;
        }
        if (couponFromDB.getAmount() <= 0 ) {// check if left from this coupon for selling
            System.out.println("There are no coupons left to sell");
            return;
        }
        ArrayList<Integer> allPurchaseOfTheCustomer = couponDBDAO().getAllPurchase(customerId);
        for (Integer ref: allPurchaseOfTheCustomer) {
            if (coupon.getId() == ref) {
                System.out.println("This coupon has already been purchased!");
                return;
            }
        }
        couponDBDAO().addCouponPurchase(customerId, coupon.getId());
        int amount = coupon.getAmount();
        coupon.setAmount(amount-1);
        couponDBDAO().updateCoupon(coupon);
    }

    public ArrayList<Coupon> getCustomerCoupons() throws SQLException, NotExistException {
        ArrayList<Coupon> coupons = new ArrayList<>();
        ArrayList<Integer> allCouponsPurchasedByCustomer = couponDBDAO().getAllPurchase(customerId);
        for (Integer ref: allCouponsPurchasedByCustomer){
            coupons.add(couponDBDAO().getOneCoupon(ref));
        }
        return coupons;
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException, NotExistException {
        ArrayList<Integer> allCouponsPurchasedByCustomer = couponDBDAO().getAllPurchase(customerId);
        ArrayList<Coupon> couponsByCategory = new ArrayList<>();
        for (Integer ref: allCouponsPurchasedByCustomer){
            if (couponDBDAO().getOneCoupon(ref).getCategory() == category) // check if the category of the coupon is similar to the category we have got
                couponsByCategory.add(couponDBDAO().getOneCoupon(ref));
        }
        if (couponsByCategory.isEmpty()){
            throw new NotExistException("There are no such coupons for this customer");
        }
        return couponsByCategory;
    }

    /**
     *
     * @param "double max price the customer spend on the most expensive coupon
     * @return all coupons that cheap from the most expensive coupon
     * @throws SQLException
     * @throws NotExistException
     */
    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException, NotExistException {
        ArrayList<Integer> allCouponsPurchasedByCustomer = couponDBDAO().getAllPurchase(customerId);
        ArrayList<Coupon> couponsCheapestFromMaxPrice = new ArrayList<>();
        for (Integer ref: allCouponsPurchasedByCustomer){
            if (couponDBDAO().getOneCoupon(ref).getPrice() < maxPrice)
                couponsCheapestFromMaxPrice.add(couponDBDAO().getOneCoupon(ref));
        }
        if (couponsCheapestFromMaxPrice.isEmpty()){
            throw new NotExistException("There are no such coupons for this customer");
        }
        return couponsCheapestFromMaxPrice;
    }

    public Customer getCustomerDetails() throws SQLException {
        return customerDBDAO().getOneCustomer(customerId);
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
