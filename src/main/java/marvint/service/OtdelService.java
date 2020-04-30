package marvint.service;

import marvint.domain.Employee;
import marvint.domain.Otdel;
import marvint.domain.Otdel;
import marvint.repository.OtdelRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OtdelService {

    @Autowired
    OtdelRepository otdelRepository;

    public Otdel getOtdel(Long id) {
        return otdelRepository.findById(id).get();
    }


    public Otdel createOtdel(Otdel create) {
        return otdelRepository.save(create);
    }

    public void saveOtdel(Otdel otdel) {
        otdelRepository.save(otdel);
    }

    public List<Otdel> listAll() {
        List<Otdel> list = new ArrayList<>();
        otdelRepository.findAll().forEach(list::add);
        return list;
    }

    public void deleteOtdel(Long id) {
        otdelRepository.deleteById(id);
    }


    @Transactional
    public List<Otdel> listAllOtdels() {
        List<Otdel> list = new ArrayList<>();
        otdelRepository.findAll().forEach(list::add);
        for (var otdel :
                list) {
            Hibernate.initialize(otdel.getEmployees());
            otdel.getEmployees().forEach(e -> {
                List<Employee> employees = otdel.getEmployees();
                Hibernate.initialize(employees);
                employees.forEach(employee -> {
                    Hibernate.initialize(employee.getMail());
                    Hibernate.initialize(employee.getPhone());
                });
            });
        }
        return list;
    }

}
