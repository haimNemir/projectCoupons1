package Utils;

import Beans.Coupon;
import DAL.Coupons.CouponDBDAO;
import Exceptions.CouponException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Thread.*;

public class CouponExpirationDailyJob implements Runnable {
    private CouponDBDAO couponDBDAO = new CouponDBDAO();
    private boolean running = true;

    public CouponExpirationDailyJob() throws SQLException {
    }

    @Override
    public void run() {
        while(running){
            try {
                ArrayList<Coupon> allCoupons = couponDBDAO.getAllCoupons();
                Date dateNow = new Date();
                for (Coupon ref: allCoupons){
                    if (ref.getEndDate().before(dateNow)){ // check if the expiry date is left
                        couponDBDAO.deleteCouponPurchaseByCouponID(ref.getId());
                        couponDBDAO.deleteCoupon(ref.getId());
                    }
                }
                sleep(3_600_000 * 24); // 24 hours
            } catch (SQLException | CouponException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!!");
                if (!running){
                    break;
                }
            }

        }
    }

    public void stop(){
            running = false;
    }
}
