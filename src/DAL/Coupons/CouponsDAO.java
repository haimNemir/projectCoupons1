package DAL.Coupons;

import Beans.Coupon;
import Exceptions.CouponException;
import Exceptions.NotExistException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon) throws SQLException, CouponException;
    public void updateCoupon(Coupon coupon) throws SQLException, NotExistException, CouponException;
    public void deleteCoupon(int couponID) throws SQLException, CouponException;
    public ArrayList<Coupon> getAllCoupons() throws SQLException;
    public Coupon getOneCoupon(int couponID) throws SQLException, NotExistException;
    public void addCouponPurchase(int customerID, int couponID) throws SQLException, CouponException;
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException, CouponException;
}
