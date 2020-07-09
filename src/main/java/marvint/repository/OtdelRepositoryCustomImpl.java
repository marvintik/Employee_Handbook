package marvint.repository;

import marvint.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class OtdelRepositoryCustomImpl implements OtdelRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Otdel> findByFilter(OtdelFilter otdelFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select v from Otdel v");
        sb.append(" where 1=1");
        if (otdelFilter.getId() != null) {
            sb.append(" and v.id = :id");
        }

        if (otdelFilter.getTitle() != null && otdelFilter.getTitle().length() != 0) {
            sb.append(" and v.title = :title");
        }
        if (otdelFilter.getAddress() != null && otdelFilter.getAddress().length() != 0) {
            sb.append(" and v.address = :address");
        }
        if (otdelFilter.getDepartment() != null && otdelFilter.getDepartment().length() != 0) {
            sb.append(" and v.department = :department");
        }

        TypedQuery<Otdel> query = entityManager.createQuery(sb.toString(), Otdel.class);
        if (otdelFilter.getId() != null) {
            query.setParameter("id", otdelFilter.getId());
        }

        if (otdelFilter.getTitle() != null && otdelFilter.getTitle().length() != 0) {
            query.setParameter("title", otdelFilter.getTitle());
        }
        if (otdelFilter.getAddress() != null && otdelFilter.getAddress().length() != 0) {
            query.setParameter("address", otdelFilter.getAddress());
        }
        if (otdelFilter.getDepartment() != null && otdelFilter.getDepartment().length() != 0) {
            query.setParameter("department", otdelFilter.getDepartment());
        }

        return query.getResultList();
    }
}
