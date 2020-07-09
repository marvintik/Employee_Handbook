package marvint.repository;

import marvint.domain.Employee;
import marvint.domain.EmployeeFilter;
import marvint.domain.Otdel;
import marvint.domain.OtdelFilter;

import java.util.List;

public interface OtdelRepositoryCustom {
    List<Otdel> findByFilter(OtdelFilter otdelFilter);
}
