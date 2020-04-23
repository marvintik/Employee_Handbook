package marvint.service;

import marvint.domain.Department;
import marvint.domain.Department;
import marvint.domain.Employee;
import marvint.repository.DepartmentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).get();
    }


    public Department createDepartment(Department create) {
        return departmentRepository.save(create);
    }

    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    public List<Department> listAll() {
        return (List<Department>) departmentRepository.findAll();
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Transactional
    public List<Department> listAllDepartments() {
        List<Department> list = new ArrayList<>();
        departmentRepository.findAll().forEach(list::add);
        for (var department :
                list) {
            Hibernate.initialize(department.getOtdel());
            department.getOtdel().forEach(o -> {
                List<Employee> employees = o.getEmployees();
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
