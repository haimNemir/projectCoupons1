package DAL.Company;

import Beans.Company;
import DB.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {
    private ConnectionPool pool = ConnectionPool.getInstance();

    public CompaniesDBDAO() throws SQLException {
    }

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.companies where email = (?) and password = (?);");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            return (rs.next());
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("insert into couponsdb.companies(name, email, password) values(?, ?, ?);");
            statement.setString(1, company.getName());
            statement.setString(2, company.getEmail());
            statement.setString(3, company.getPassword());
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void updateCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("update couponsdb.companies set email = (?), password = (?) where id = (?);");
            statement.setString(1, company.getEmail());
            statement.setString(2, company.getPassword());
            statement.setInt(3, company.getId());
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public void deleteCompany(int companyID) throws SQLException {
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("delete from couponsdb.companies where id = (?);");
            statement.setInt(1, companyID);
            statement.execute();
        } finally {
            pool.restoreConnection(con);
        }
    }

    @Override
    public ArrayList<Company> getAllCompanies() throws SQLException {
        ArrayList<Company> companies = new ArrayList<>();
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.companies");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                companies.add(new Company(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        null));
            }
        } finally {
            pool.restoreConnection(con);
        }
        return companies;
    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException {
        Company company = null;
        Connection con = pool.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from couponsdb.companies where id = (?);");
            statement.setInt(1, companyID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                company = new Company(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        null);
            }
        } finally {
            pool.restoreConnection(con);
        }
        return company;
    }
}
