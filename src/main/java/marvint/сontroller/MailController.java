package marvint.сontroller;

import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/v1/mails")
public class MailController {

    @Autowired
    MailService mailService;


    @GetMapping("/(id}")
    public Mail getMail(@PathVariable Long id) {
        return mailService.getMail(id);
    }

    @GetMapping
    public ModelAndView home() {
        List<Mail> listMail = mailService.listAll();
        ModelAndView mav = new ModelAndView("mail");
        mav.addObject("listMail", listMail);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView filter() {
        ModelAndView mav = new ModelAndView("search"/*, "command", new EmployeeFilter()*/);
        return mav;
    }


    public void deleteByEmployee(Employee employee) {
        mailService.deleteByEmployee(employee);
    }

    public List<Mail> getMailList() {
        return mailService.listAllMails();
    }

    public void saveMail(Mail mail) {
        mailService.saveMail(mail);
    }

}
