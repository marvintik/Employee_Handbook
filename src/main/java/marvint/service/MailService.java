package marvint.service;

import marvint.domain.Mail;
import marvint.domain.Filter;
import marvint.repository.MailRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {

    @Autowired
    MailRepository mailRepository;


    public Mail getMail(Long id) {
        return mailRepository.findById(id).get();
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

    public void deleteMail(Long id){
        mailRepository.deleteById(id);
    }


    @Transactional
    public List<Mail> listAllMails() {
        List<Mail> list = new ArrayList<>();
        mailRepository.findAll().forEach(list::add);
        return list;
    }
}
