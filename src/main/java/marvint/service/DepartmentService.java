package marvint.service;

import marvint.domain.Department;
import marvint.domain.Department;
import marvint.domain.DepartmentFilter;
import marvint.domain.Employee;
import marvint.exceptions.EntityAlreadyExistException;
import marvint.exceptions.EntityNotFoundException;
import marvint.repository.DepartmentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional
    public Department getDepartment(Long id) throws EntityNotFoundException {
        if (departmentRepository.findById(id).isPresent()) {
            return departmentRepository.findById(id).get();
        } else throw new EntityNotFoundException(Department.class, id);
    }


    public Department createDepartment(Department create) {
        return departmentRepository.save(create);
    }


    public void saveDepartment(Department department) {
        Department updateDepartment = departmentRepository.findById(department.getId()).orElseThrow();
        updateDepartment.setTitle(department.getTitle());
        updateDepartment.setAddress(department.getAddress());
        departmentRepository.save(updateDepartment);
    }

    public Department getByTitle(String title) {
        return departmentRepository.findByTitle(title);
    }

    public List<Department> listAll() {
        return (List<Department>) departmentRepository.findAll();
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public List<Department> listAllDepartments() {
        List<Department> list = new ArrayList<>();
        departmentRepository.findAll().forEach(list::add);
        list.sort(Comparator.comparing(Department::getId));
        return list;
    }

    public List<Department> listDepartmentByFilter(DepartmentFilter departmentFilter) {
        var list = departmentRepository.findByFilter(departmentFilter);
        list.sort(Comparator.comparing(Department::getId));
        return list;
    }
    public Long count() {
        Long count = departmentRepository.count();
        return count;
    }

}
