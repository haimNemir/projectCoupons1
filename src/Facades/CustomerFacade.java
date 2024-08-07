//package Facades;
//
//import DAL.Company.CompaniesDAO;
//import DAL.Coupons.CouponsDAO;
//import DAL.Customer.CustomerDAO;
//
//public class CustomerFacade extends ClientFacade {
//    @Override
//    public boolean login(String email, String password) {
//        return false;
//    }
//
//    @Override
//    protected CompaniesDAO companiesDAO() {
//        return null;
//    }
//
//
//    @Override
//    protected CustomerDAO customerDAO() {
//        return null;
//    }
//
//    @Override
//    protected CouponsDAO couponsDAO() {
//        return null;
//    }

//// public..addCoupons using DAO
//    א. לוודא שהלקוח לא רכש כבר בעבר קופון כזה.
//    ב. לוודא שהקופון הדרוש עדיין קיים במלאי )הכמות שלו גדולה מ0-(.
//    ג. לוודא שתאריך התפוגה של הקופון עדיין לא הגיע.
//    ד. לבצע את רכישת הקופון ע"י הלקוח.
//    ה. להוריד את הכמות במלאי של הקופון ב.
//}
