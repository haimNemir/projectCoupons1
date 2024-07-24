package DAL;

import beans.Company;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        try {
            Company company = new Company(6,"Google", "google@gmail.com", "14327842", null);
            CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
            companiesDBDAO.deleteCompany(6);
















        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}