package marvint.service;

import marvint.domain.*;
import marvint.domain.Otdel;
import marvint.repository.OtdelRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class OtdelService {

    @Autowired
    OtdelRepository otdelRepository;

    public Otdel getOtdel(Long id) {
        Otdel otdel = otdelRepository.findById(id).orElseThrow();
        return otdel;
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

    public Otdel getByTitle(String title) {
        return otdelRepository.findByTitle(title);
    }

    public List<Otdel> listAllOtdels() {
        List<Otdel> list = new ArrayList<>();
        otdelRepository.findAll().forEach(list::add);
        return list;
    }

    public Otdel getOtdelByTitleAndDepantment(String title, Department department) {
        return otdelRepository.findByTitleAndDepartment(title, department);
    }

    public List<Otdel> getOtdelsByFilter(OtdelFilter otdelFilter) {
        var list = otdelRepository.findByFilter(otdelFilter);
        list.sort(Comparator.comparing(Otdel::getId));
        return list;
    }

    public Long count() {
        Long count = otdelRepository.count();
        return count;
    }

}
