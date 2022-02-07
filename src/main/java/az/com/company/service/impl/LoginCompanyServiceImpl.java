package az.com.company.service.impl;

import az.com.company.enums.EnumAvailableStatus;
import az.com.company.model.LoginCompany;
import az.com.company.repository.LoginCompanyDao;
import az.com.company.request.RequestLogin;
import az.com.company.response.ResponseLogin;
import az.com.company.response.ResponseStatus;
import az.com.company.service.inter.LoginCompanyService;
import az.com.company.util.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginCompanyServiceImpl implements LoginCompanyService {

    @Autowired
    private LoginCompanyDao loginCompanyDao;

    @Override
    public ResponseLogin login(RequestLogin requestLogin) {
        ResponseLogin response = new ResponseLogin();
        String username = requestLogin.getUsername();
        String password = requestLogin.getPassword();
        try {
            if (username == null || password == null) {
                response.setStatus(new ResponseStatus(ExceptionConstants.USERNAME_OR_PASSWORD_IS_EMPTY, "Username or password is empty!"));
                return response;
            }
            LoginCompany loginCompany = loginCompanyDao.findLoginCompanyByUsernameAndPasswordAndActive(username, password, EnumAvailableStatus.ACTIVE.getValue());
            if (loginCompany == null) {
                response.setStatus(new ResponseStatus(ExceptionConstants.INVALID_USERNAME_OR_PASSWORD, "Invalid username or password"));
                return response;
            }
            if (loginCompany.getToken() != null && !loginCompany.getToken().isEmpty()) {
                response.setStatus(new ResponseStatus(ExceptionConstants.SESSION_IS_ALREADY_EXIST, "Session is already exist"));
                response.setUserId(loginCompany.getId());
                response.setToken(loginCompany.getToken());
                return response;
            }
            String token = UUID.randomUUID().toString();
            loginCompany.setToken(token);
            loginCompanyDao.save(loginCompany);
            response.setUserId(loginCompany.getId());
            response.setToken(token);
            response.setUsername(loginCompany.getUsername());
            response.setCompanyName(loginCompany.getCompanyName());
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public ResponseStatus logout(String token) {
        ResponseStatus response = null;
        try {
            if (token == null) {
                response = new ResponseStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
                return response;
            }
            LoginCompany loginCompany = loginCompanyDao.findLoginCompanyByTokenAndActive(token, EnumAvailableStatus.ACTIVE.getValue());
            if (loginCompany == null) {
                response = new ResponseStatus(ExceptionConstants.INVALID_TOKEN, "Invalid token");
                return response;
            }
            loginCompany.setToken(null);
            loginCompanyDao.save(loginCompany);
            response = ResponseStatus.getSuccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            response = new ResponseStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception");
        }
        return response;
    }
}
