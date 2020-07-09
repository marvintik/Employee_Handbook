package marvint.repository;

import marvint.domain.Department;
import marvint.domain.DepartmentFilter;
import marvint.domain.Department;
import marvint.domain.DepartmentFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class DepartmentRepositoryCustomImpl implements DepartmentRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Department> findByFilter(DepartmentFilter departmentFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select v from Department v");
        sb.append(" where 1=1");
        if (departmentFilter.getId() != null) {
            sb.append(" and v.id = :id");
        }

        if (departmentFilter.getTitle() != null && departmentFilter.getTitle().length() != 0) {
            sb.append(" and v.title = :title");
        }
        if (departmentFilter.getAddress() != null && departmentFilter.getAddress().length() != 0) {
            sb.append(" and v.address = :address");
        }

        TypedQuery<Department> query = entityManager.createQuery(sb.toString(), Department.class);
        if (departmentFilter.getId() != null) {
            query.setParameter("id", departmentFilter.getId());
        }

        if (departmentFilter.getTitle() != null && departmentFilter.getTitle().length() != 0) {
            query.setParameter("title", departmentFilter.getTitle());
        }
        if (departmentFilter.getAddress() != null && departmentFilter.getAddress().length() != 0) {
            query.setParameter("address", departmentFilter.getAddress());
        }

        return query.getResultList();
    }
}
