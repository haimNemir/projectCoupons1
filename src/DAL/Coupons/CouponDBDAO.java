package DAL.Coupons;

import Exceptions.NotExistException;
import Utils.CreateDate;
import Utils.Category;
import Beans.Coupon;
import DB.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CouponDBDAO implements CouponsDAO {
    private ConnectionPool pool = ConnectionPool.getInstance();

    public CouponDBDAO() throws SQLException {
    }

    @Override
    public void addCoupon(Coupon coupon) throws SQLException {
        Connection con = pool.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//change the Date to the format pattern
        String formattedDate1 = sdf.format(coupon.getStartDate());
        String formattedDate2 = sdf.format(coupon.getEndDate());
        try {
            PreparedStatement statement = con.prepareStatement("insert into couponsdb.coupons(company_id, category_id, title, description, start_date, end_date, amount, price, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setInt(1, coupon.getCompanyID());
            statement.setInt(2, Category.getIdFromCategory(coupon.getCategory()));
            statement.setString(3, coupon.getTitle());
            statement.setString(4, coupon.getDescription());
            statement.setString(5, formattedDate1);
            statement.setString(6, formattedDate2);
            statement.setInt(7, coupon.getAmount());
            statement.setDouble(8, coupon.getPrice());
            statement.setString(9, coupon.getImage());
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void updateCoupon(Coupon coupon) throws SQLException, NotExistException {
        Connection con = pool.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//change the Date to the format pattern
        String formattedDate1 = sdf.format(coupon.getStartDate());
        String formattedDate2 = sdf.format(coupon.getEndDate());
        try {
            PreparedStatement statement = con.prepareStatement("update couponsdb.coupons set company_id = (?), category_id = (?), title = (?), description = (?), start_date = (?), end_date = (?), amount = (?), price = (?), image = (?) where id = (?);");
            statement.setInt(1, coupon.getCompanyID());
            statement.setInt(2, Category.getIdFromCategory(coupon.getCategory()));
            statement.setString(3, coupon.getTitle());
            statement.setString(4, coupon.getDescription());
            statement.setString(5, formattedDate1);
            statement.setString(6, formattedDate2);
            statement.setInt(7, coupon.getAmount());
            statement.setDouble(8, coupon.getPrice());
            statement.setString(9, coupon.getImage());
            statement.setInt(10, coupon.getId());
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * Delete Coupon from Coupons table in the DB by coupon ID.
     * @param couponID
     * @throws SQLException
     */
    @Override
    public void deleteCoupon(int couponID) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("delete from couponsdb.coupons where id = (?);");
            statement.setInt(1, couponID);
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * @return ArrayList of Coupons , each Coupon contain start_date and end_date and saved in this pattern: "Mon Jul 29 00:00:00 IDT 2024",
     * @throws SQLException
     */
    @Override
    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        ArrayList<Coupon> coupons = new ArrayList<>();
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.coupons");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                coupons.add(new Coupon(
                        rs.getInt(1),
                        rs.getInt(2),
                        Category.getCategoryFromID(rs.getInt(3)),
                        rs.getString(4),
                        rs.getString(5),
                        CreateDate.getDateFromString(rs.getString(6)),
                        CreateDate.getDateFromString(rs.getString(7)),
                        rs.getInt(8),
                        rs.getDouble(9),
                        rs.getString(10)));
            }
        } finally {
            pool.restoreConnection(con);
        }
        return coupons;
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws SQLException, NotExistException {
        Coupon coupon = null;
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.coupons where id = (?);");
            statement.setInt(1, couponID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                coupon = new Coupon(
                        rs.getInt(1),
                        rs.getInt(2),
                        Category.getCategoryFromID(rs.getInt(3)),
                        rs.getString(4),
                        rs.getString(5),
                        CreateDate.getDateFromString(rs.getString(6)),
                        CreateDate.getDateFromString(rs.getString(7)),
                        rs.getInt(8),
                        rs.getDouble(9),
                        rs.getString(10));
            } else throw new NotExistException("The coupon ID you entered does not exist in the system");
        } finally {
            pool.restoreConnection(con);
        }
        return coupon;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("insert into couponsdb.customer_vs_coupons(customer_id, coupons_id) values(?, ?);");
            statement.setInt(1, customerID);
            statement.setInt(2, couponID);
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * Delete Coupon purchase by Coupon ID and customer ID.
     * @param customerID
     * @param couponID
     * @throws SQLException
     */
    @Override
    public void deleteCouponPurchase(int customerID, int couponID) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("delete from couponsdb.customer_vs_coupons where customer_id = (?) and coupons_id = (?);");
            statement.setInt(1, customerID);
            statement.setInt(2, couponID);
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     * Delete Coupon purchase by Coupon ID.
     * @param couponID
     * @throws SQLException
     */
    public void deleteCouponPurchaseByCouponID(int couponID) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("delete from couponsdb.customer_vs_coupons where coupons_id = (?);");
            statement.setInt(1, couponID);
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    public void deleteCouponPurchaseByCustomerID(int customerID) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("delete from couponsdb.customer_vs_coupons where customer_id = (?);");
            statement.setInt(1, customerID);
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    /**
     *
     * @param customerId
     * @return the coupons the customer purchase
     * @throws SQLException
     */
    public ArrayList<Integer> getAllPurchase(int customerId) throws SQLException {
        ArrayList<Integer> purchasedCoupons = new ArrayList<>();
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select coupons_id from couponsdb.customer_vs_coupons where customer_id = ?;");
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                purchasedCoupons.add(rs.getInt(1));
            }
        } finally {
            pool.restoreConnection(con);
        }
        return purchasedCoupons;
    }




}
