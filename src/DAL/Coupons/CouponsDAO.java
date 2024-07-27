package DAL.Coupons;

import beans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon) throws SQLException;
    public void updateCoupon(Coupon coupon) throws SQLException;
    public void deleteCoupon(int couponID) throws SQLException;
    public ArrayList<Coupon> getAllCoupons() throws SQLException;
    public Coupon getOneCoupon(int couponID) throws SQLException;
    public Void addCouponPurchase(int customerID, int couponID) throws SQLException;
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException;
}
