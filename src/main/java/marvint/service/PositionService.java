package marvint.service;

import marvint.domain.Position;
import marvint.exceptions.EntityNotFoundException;
import marvint.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    public Position getPosition(Long id) throws EntityNotFoundException {
        if (positionRepository.findById(id).isPresent()) return positionRepository.findById(id).get();
        else throw new EntityNotFoundException(Position.class, id);
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

    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }

    public List<Position> listAllPositions() {
        List<Position> list = new ArrayList<>();
        positionRepository.findAll().forEach(list::add);
        return list;
    }

    public Long count() {
        Long count = positionRepository.count();
        return count;
    }
}
