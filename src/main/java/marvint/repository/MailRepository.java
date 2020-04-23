package marvint.repository;

import marvint.domain.Mail;
import marvint.domain.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {
}
