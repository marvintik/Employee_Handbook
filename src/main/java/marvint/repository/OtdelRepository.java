package marvint.repository;

import marvint.domain.Otdel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtdelRepository extends CrudRepository<Otdel, Long> {
}
