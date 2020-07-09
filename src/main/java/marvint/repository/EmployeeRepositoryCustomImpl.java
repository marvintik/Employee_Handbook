package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.EmployeeFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findByFilter(EmployeeFilter employeeFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select v from Employee v");

        if(employeeFilter.getPhone().length() != 0 || employeeFilter.getMail().length() != 0) {
            if (employeeFilter.getPhone() != null && employeeFilter.getPhone().length() != 0) {
                sb.append(" join v.phone p on p.phone in :phone");
            }
            if (employeeFilter.getMail() != null && employeeFilter.getMail().length() != 0) {
                sb.append(" join v.mail m on m.mail in :mail");
            }
        } else sb.append(" where 1=1");

        if (employeeFilter.getLogin() != null && employeeFilter.getLogin().length() != 0) {
            sb.append(" and v.login = :login");
        }

        if (employeeFilter.getFirstName() != null && employeeFilter.getFirstName().length() != 0) {
            sb.append(" and v.firstName = :firstName");
        }
        if (employeeFilter.getLastName() != null && employeeFilter.getLastName().length() != 0) {
            sb.append(" and v.lastName = :lastName");
        }
        if (employeeFilter.getSecondName() != null && employeeFilter.getSecondName().length() != 0) {
            sb.append(" and v.secondName = :secondName");
        }

        TypedQuery<Employee> query = entityManager.createQuery(sb.toString(), Employee.class);
        if (employeeFilter.getLogin() != null && employeeFilter.getLogin().length() != 0) {
            query.setParameter("login", employeeFilter.getLogin());
        }

        if (employeeFilter.getFirstName() != null && employeeFilter.getFirstName().length() != 0) {
            query.setParameter("firstName", employeeFilter.getFirstName());
        }
        if (employeeFilter.getLastName() != null && employeeFilter.getLastName().length() != 0) {
            query.setParameter("lastName", employeeFilter.getLastName());
        }
        if (employeeFilter.getSecondName() != null && employeeFilter.getSecondName().length() != 0) {
            query.setParameter("secondName", employeeFilter.getSecondName());
        }

        if (employeeFilter.getMail() != null && employeeFilter.getMail().length() != 0) {
            query.setParameter("mail", employeeFilter.getMail());
        }
        if (employeeFilter.getPhone() != null && employeeFilter.getPhone().length() != 0) {
            query.setParameter("phone", employeeFilter.getPhone());
        }

        return query.getResultList();
    }
}
