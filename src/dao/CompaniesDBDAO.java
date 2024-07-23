package dao;

import beans.Company;
import db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompaniesDBDAO {
    private ConnectionPool pool = ConnectionPool.getInstance();

    public void addCompany(Company company) throws SQLException {
        Connection con = pool.getConnection();

        PreparedStatement statement = con.prepareStatement("insert into couponsdb.companies(name, email, password) values(?, ?, ?);");
        statement.execute();

        pool.restoreConnection(con);
    }

    public Company getOneCompany(int id) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement statement = con.prepareStatement("select * from couponsdb.companies where id = (?);");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Company company = null;
        if (rs.next()) {
            company = new Company(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), null);
        }
        pool.restoreConnection(con);
        return company;
    }
}
