package DAL.Company;

import Beans.Company;
import Exceptions.CompanyException;

import java.sql.SQLException;
import java.util.ArrayList;

public  interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password) throws SQLException;
    public void addCompany(Company company) throws SQLException, CompanyException;
    public void updateCompany(Company company) throws SQLException, CompanyException;
    public void deleteCompany(int companyID) throws SQLException, CompanyException;
    public ArrayList<Company> getAllCompanies() throws SQLException;
    public Company getOneCompany(int companyID) throws SQLException;
}
