package az.com.company.repository;

import az.com.company.model.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentDao extends CrudRepository<Payment,Long> {
    List<Payment> findAllByActive(Integer active);

    Payment findPaymentByIdAndActive(Long id,Integer active);
}
