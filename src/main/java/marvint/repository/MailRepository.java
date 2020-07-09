package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.domain.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {
    List<Mail> findMailByEmployee(Employee employee);
}
