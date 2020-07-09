package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.EmployeeFilter;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findByFilter(EmployeeFilter employeeFilter);
}
