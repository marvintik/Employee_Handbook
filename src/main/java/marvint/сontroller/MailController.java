package marvint.сontroller;

import marvint.domain.Mail;
import marvint.domain.Filter;
import marvint.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/v1/mails")
public class MailController {

    @Autowired
    MailService mailService;


    @GetMapping("/(id}")
    public Mail getMail(@PathVariable Long id) {
        return mailService.getMail(id);
    }

    @PostMapping
    public Mail createMovie(@RequestBody Mail mail) {
        return mailService.createMail(mail);
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
        ModelAndView mav = new ModelAndView("search"/*, "command", new Filter()*/);
        //System.out.println(filter());
        return mav;
    }



    public List<Mail> getMailList() {
        return mailService.listAllMails();
    }

    public void saveMail(Mail mail) {
        mailService.saveMail(mail);
    }

}
