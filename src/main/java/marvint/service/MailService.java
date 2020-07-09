package marvint.service;

import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MailService {

    @Autowired
    MailRepository mailRepository;

    public Mail getMail(Long id) {
        return mailRepository.findById(id).orElseThrow();
    }

    public Mail createMail(Mail create) {
        return mailRepository.save(create);
    }

    public void saveMail(Mail mail) {
        mailRepository.save(mail);
    }

    public List<Mail> listAll() {
        List<Mail> list = new ArrayList<>();
        mailRepository.findAll().forEach(list::add);
        return list;
    }

    public void deleteMail(Long id) {
        mailRepository.deleteById(id);
    }

    public void deleteByEmployee(Employee employee) {
        var mailList = mailRepository.findMailByEmployee(employee);
        mailList.forEach(mail -> mailRepository.delete(mail));
    }

    public List<Mail> listAllMails() {
        List<Mail> list = new ArrayList<>();
        mailRepository.findAll().forEach(list::add);
        return list;
    }
}
