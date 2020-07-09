package marvint.utils;

import marvint.domain.Employee;
import marvint.domain.Mail;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public final class EmailUtil {

    public static String joinMail(List<Mail> mails) {
        StringBuilder s = new StringBuilder();
        if (mails.size() == 0)
            return s.toString();
        for (var mail : mails) {
            s.append(mail.getMail());
            s.append(", ");
        }
        return s.substring(0, s.length() - 2);
    }

    public static List<Mail> valAndSaveMails(Employee newEmployee, String mailText) {
        var mails = mailText.split(", *");
        List<Mail> mailList = new ArrayList<Mail>();
        for (String mail : mails) {
            if (mail.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}")) {
                Mail newMail = new Mail();
                newMail.setMail(mail);
                newMail.setEmployee(newEmployee);
                mailList.add(newMail);
            }
        }
        return mailList;
    }
}
