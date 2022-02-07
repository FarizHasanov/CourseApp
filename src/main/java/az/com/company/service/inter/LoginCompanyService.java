package az.com.company.service.inter;

import az.com.company.request.RequestLogin;
import az.com.company.response.ResponseLogin;
import az.com.company.response.ResponseStatus;

public interface LoginCompanyService {

    ResponseLogin login(RequestLogin requestLogin);

    ResponseStatus logout(String token);
}
