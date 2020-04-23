package marvint.service;

import marvint.domain.Phone;
import marvint.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;


    public Phone getPhone(Long id) {
        return phoneRepository.findById(id).get();
    }


    public Phone createPhone(Phone create) {
        return phoneRepository.save(create);
    }

    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    public List<Phone> listAll() {
        List<Phone> list = new ArrayList<>();
        phoneRepository.findAll().forEach(list::add);
        return list;
    }

    public void deletePhone(Long id){
        phoneRepository.deleteById(id);
    }
    

    @Transactional
    public List<Phone> listAllPhones() {
        List<Phone> list = new ArrayList<>();
        phoneRepository.findAll().forEach(list::add);
        return list;
    }
}
