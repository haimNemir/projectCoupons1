package DAL.Coupons;

import Beans.Coupon;
import Exceptions.NotExistException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon) throws SQLException;
    public void updateCoupon(Coupon coupon) throws SQLException, NotExistException;
    public void deleteCoupon(int couponID) throws SQLException;
    public ArrayList<Coupon> getAllCoupons() throws SQLException;
    public Coupon getOneCoupon(int couponID) throws SQLException, NotExistException;
    public void addCouponPurchase(int customerID, int couponID) throws SQLException;
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException;
}
