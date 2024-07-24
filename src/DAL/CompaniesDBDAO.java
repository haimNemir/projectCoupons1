package DAL;
import beans.Company;
import db.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {
    private ConnectionPool pool;
    {
        try {
            pool = ConnectionPool.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        Connection con = pool.getConnection();
        boolean isExists = false;
        PreparedStatement statement = con.prepareStatement("select * from couponsdb.companies where email = (?) and password = (?);");
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            isExists = true;
        }
        pool.restoreConnection(con);
        return isExists;
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("insert into couponsdb.companies(name, email, password) values(?, ?, ?);");
        statement.setString(1, company.getName());
        statement.setString(2, company.getEmail());
        statement.setString(3, company.getPassword());
        statement.execute();
        pool.restoreConnection(con);
    }

    @Override
    public void updateCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("update couponsdb.companies set name = (?), email = (?), password = (?) where id = (?);");
        statement.setString(1, company.getName());
        statement.setString(2, company.getEmail());
        statement.setString(3, company.getPassword());
        statement.setInt(4, company.getId());
        statement.execute();
        pool.restoreConnection(con);
    }

    @Override
    public void deleteCompany(int companyID) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("delete from couponsdb.companies where id = (?);");
        statement.setInt(1, companyID);
        statement.execute();
        pool.restoreConnection(con);
    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        return null;
    }

    @Override
    public Company getOneCompany(int companyID) {
        return null;
    }
}
