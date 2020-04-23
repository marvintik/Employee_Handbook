package marvint.service;

import marvint.domain.Employee;
import marvint.domain.Filter;
import marvint.domain.Phone;
import marvint.repository.EmployeeRepository;
import marvint.repository.MailRepository;
import marvint.repository.PhoneRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee getEmployee(String login) {
        return employeeRepository.findById(login).get();
    }


    public Employee createEmployee(Employee create) {
        return employeeRepository.save(create);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> listAll() {
        List<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        return list;
    }

    public void deleteEmployee(String login){
        employeeRepository.deleteById(login);
    }

    public List<Employee> getEmployees(Filter filter){
        return employeeRepository.findByFilter(filter);
    }

    @Transactional
    public List<Employee> listAllEmployees() {
        List<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        for (var employee :
                list) {
            Hibernate.initialize(employee.getMail());
            Hibernate.initialize(employee.getPhone());
        }
        return list;
    }
}
