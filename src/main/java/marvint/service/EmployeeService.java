package marvint.service;

import marvint.domain.Employee;
import marvint.domain.EmployeeFilter;
import marvint.domain.Position;
import marvint.exceptions.EntityAlreadyExistException;
import marvint.exceptions.EntityNotFoundException;
import marvint.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee getEmployee(String login) throws EntityNotFoundException {
        if (employeeRepository.findById(login).isPresent()) {
            return employeeRepository.findById(login).get();
        } else throw new EntityNotFoundException(Employee.class, login);
    }


    public Employee createEmployee(Employee create) throws EntityAlreadyExistException {
        if (!employeeRepository.existsById(create.getLogin())) {
            return employeeRepository.save(create);
        } else throw new EntityAlreadyExistException(Employee.class, create.getLogin());
    }

    public void updateEmployee(Employee employee) throws EntityNotFoundException {
        if (employeeRepository.findById(employee.getLogin()).isPresent()) {
            employeeRepository.save(employee);
        } else throw new EntityNotFoundException(Employee.class, employee.getLogin());
    }

    public List<Employee> listAll() {
        List<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        list.sort(Comparator.comparing(Employee::getLogin));
        return list;
    }

    public void deleteEmployee(String login) throws EntityNotFoundException {
        if (employeeRepository.findById(login).isPresent()) {
            employeeRepository.deleteById(login);
        } else throw new EntityNotFoundException(Employee.class, login);
    }

    public List<Employee> getEmployees(EmployeeFilter employeeFilter) {
        var list = employeeRepository.findByFilter(employeeFilter);
        list.sort(Comparator.comparing(Employee::getLogin));
        return list;
    }

    @Transactional
    public List<Employee> listAllEmployees() {
        List<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        list.sort(Comparator.comparing(Employee::getLogin));
        return list;
    }

    public List<Employee> listEmployeeByPosition(Position position) {
        return employeeRepository.findEmployeeByPosition(position);
    }
    public Long count() {
        Long count = employeeRepository.count();
        return count;
    }
}
