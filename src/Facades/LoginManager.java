package Facades;

import Exceptions.NotExistException;

import java.sql.SQLException;

public class LoginManager {
    private static LoginManager instance;

    private LoginManager() {
    }

    public static LoginManager getInstance(){
        if (instance == null){
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws SQLException, NotExistException {
        switch (clientType){
            case Administrator:
                if (new AdminFacade().login(email,password)) {
                    AdminFacade adminFacade = new AdminFacade();
                    adminFacade.login(email,password);
                    return adminFacade;
                } else return null;
            case Company:
                if (new CompanyFacade().login(email,password)) {
                    CompanyFacade companyFacade = new CompanyFacade();
                    companyFacade.login(email, password);
                    return companyFacade;
                } else return null;
            case Customer:
                if (new CustomerFacade().login(email,password)){
                    CustomerFacade customerFacade = new CustomerFacade();
                    customerFacade.login(email, password);
                    return customerFacade;
                } else return null;
        }
        return null;
    }
}
