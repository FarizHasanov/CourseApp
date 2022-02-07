package az.com.company.controller;

import az.com.company.request.RequestLogin;
import az.com.company.response.ResponseLogin;
import az.com.company.response.ResponseStatus;
import az.com.company.service.inter.LoginCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register")
public class LoginWebServices {

    @Autowired
    private LoginCompanyService loginCompanyService;

    @RequestMapping(value = "/login" , method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseLogin login(@RequestBody RequestLogin requestLogin){
        return loginCompanyService.login(requestLogin);
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseStatus logout(@RequestParam(value = "token") String token){
        return loginCompanyService.logout(token);
    }
}
