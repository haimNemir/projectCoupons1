package DAL.Coupons;

import beans.Coupon;
import db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CouponDBDAO implements CouponsDAO{
    private ConnectionPool pool = ConnectionPool.getInstance();

    public CouponDBDAO() throws SQLException {
    }

    @Override
    public void addCoupon(Coupon coupon) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("insert into couponsdb.coupons(company_id, category_id, title, description, start_date, end_date, amount, price, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setInt(1, coupon.getCompanyID());
            statement.setInt(2, coupon.getCategory());
            statement.setString(3, coupon.getFirstName());
            statement.setString(4, coupon.getLastName());
            statement.setDate(5, coupon.getLastName());
            statement.setDate(6, coupon.getLastName());
            statement.setInt(7, coupon.getFirstName());
            statement.setDouble(8, coupon.getEmail());
            statement.setString(9, coupon.getPassword());
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) throws SQLException {

    }

    @Override
    public void deleteCoupon(int couponID) throws SQLException {

    }

    @Override
    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        return null;
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws SQLException {
        return null;
    }

    @Override
    public Void addCouponPurchase(int customerID, int couponID) throws SQLException {
        return null;
    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException {

    }
}
