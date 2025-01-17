package marvint.repository;

import marvint.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long>, DepartmentRepositoryCustom {
    Department findByTitle(String title);
}
