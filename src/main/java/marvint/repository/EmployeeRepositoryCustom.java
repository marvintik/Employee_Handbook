package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.Filter;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> findByFilter(Filter filter);
}
