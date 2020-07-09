package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.domain.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
    List<Phone> findPhoneByEmployee(Employee employee);
}
