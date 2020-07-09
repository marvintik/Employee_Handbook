package marvint.repository;

import marvint.domain.Department;
import marvint.domain.DepartmentFilter;
import marvint.domain.Employee;
import marvint.domain.EmployeeFilter;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<Department> findByFilter(DepartmentFilter departmentFilter);
}
