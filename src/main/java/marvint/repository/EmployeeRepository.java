package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>, EmployeeRepositoryCustom {
    List<Employee> findEmployeeByPosition(Position position);

}