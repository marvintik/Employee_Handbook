package marvint.utils;

import marvint.domain.Employee;
import marvint.domain.Phone;

import java.util.ArrayList;
import java.util.List;

public final class PhoneUtil {

    public static String joinPhone(List<Phone> phones) {
        StringBuilder s = new StringBuilder();
        if (phones.size() == 0)
            return s.toString();
        for (var phone : phones) {
            s.append(phone.getPhone());
            s.append(", ");
        }
        return s.substring(0, s.length() - 2);
    }

    public static List<Phone> valAndSavePhones(Employee employee, String phoneText) {
        var phones = phoneText.split(", *");
        List<Phone> phoneList = new ArrayList<Phone>();
        for (String phone : phones) {
            if (phone.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")) {
                Phone newPhone = new Phone();
                newPhone.setPhone(phone);
                newPhone.setEmployee(employee);
                phoneList.add(newPhone);
            }
        }
        return phoneList;
    }
}
