package marvint.service;

import marvint.domain.Position;
import marvint.domain.Filter;
import marvint.repository.PositionRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;


    public Position getPosition(Long id) {
        return positionRepository.findById(id).get();
    }


    public Position createPosition(Position create) {
        return positionRepository.save(create);
    }

    public void savePosition(Position position) {
        positionRepository.save(position);
    }

    public List<Position> listAll() {
        List<Position> list = new ArrayList<>();
        positionRepository.findAll().forEach(list::add);
        return list;
    }

    public void deletePosition(Long id){
        positionRepository.deleteById(id);
    }

 /**   public List<Position> getPositions(Filter filter){
        return positionRepository.findByFilter(filter);
    }*/

    @Transactional
    public List<Position> listAllPositions() {
        List<Position> list = new ArrayList<>();
        positionRepository.findAll().forEach(list::add);
        return list;
    }
}
