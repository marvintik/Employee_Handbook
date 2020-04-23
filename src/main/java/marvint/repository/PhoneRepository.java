package marvint.repository;

import marvint.domain.Mail;
import marvint.domain.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
