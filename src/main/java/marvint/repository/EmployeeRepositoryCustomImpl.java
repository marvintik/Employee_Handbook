package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.Filter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findByFilter(Filter filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select v from Employee v");

        if(filter.getPhone().length() != 0 || filter.getMail().length() != 0) {
            if (filter.getPhone() != null && filter.getPhone().length() != 0) {
                sb.append(" join v.phone p on p.phone in :phone");
            }
            if (filter.getMail() != null && filter.getMail().length() != 0) {
                sb.append(" join v.mail m on m.mail in :mail");
            }
        } else sb.append(" where 1=1");

        if (filter.getFirstName() != null && filter.getFirstName().length() != 0) {
            sb.append(" and v.firstName = :firstName");
        }
        if (filter.getLastName() != null && filter.getLastName().length() != 0) {
            sb.append(" and v.lastName = :lastName");
        }
        if (filter.getSecondName() != null && filter.getSecondName().length() != 0) {
            sb.append(" and v.secondName = :secondName");
        }






        TypedQuery<Employee> query = entityManager.createQuery(sb.toString(), Employee.class);

        if (filter.getFirstName() != null && filter.getFirstName().length() != 0) {
            query.setParameter("firstName", filter.getFirstName());
        }
        if (filter.getLastName() != null && filter.getLastName().length() != 0) {
            query.setParameter("lastName", filter.getLastName());
        }
        if (filter.getSecondName() != null && filter.getSecondName().length() != 0) {
            query.setParameter("secondName", filter.getSecondName());
        }

        if (filter.getMail() != null && filter.getMail().length() != 0) {
            query.setParameter("mail", filter.getMail());
        }
        if (filter.getPhone() != null && filter.getPhone().length() != 0) {
            query.setParameter("phone", filter.getPhone());
        }


        return query.getResultList();
    }
}
