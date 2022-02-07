package az.com.company.repository;

import az.com.company.model.LoginCompany;
import org.springframework.data.repository.CrudRepository;

public interface LoginCompanyDao extends CrudRepository<LoginCompany,Long> {

    LoginCompany findLoginCompanyByUsernameAndPasswordAndActive(String userName,String password,Integer active);

    LoginCompany findLoginCompanyByTokenAndActive(String token, Integer active);

    LoginCompany findLoginCompanyByIdAndTokenAndActive(Long id,String token,Integer active);
}
