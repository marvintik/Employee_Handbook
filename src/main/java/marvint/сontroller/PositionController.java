package marvint.сontroller;

import lombok.SneakyThrows;
import marvint.domain.Position;
import marvint.exceptions.EntityNotFoundException;
import marvint.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    PositionService positionService;

    @SneakyThrows
    @GetMapping("/(id}")
    public Position getPosition(@PathVariable Long code) {
        return positionService.getPosition(code);
    }

    @PostMapping
    public Position createMovie(@RequestBody Position position) {
        return positionService.createPosition(position);
    }

    @GetMapping
    public ModelAndView home() {
        List<Position> listPosition = positionService.listAll();
        ModelAndView mav = new ModelAndView("position");
        mav.addObject("listPosition", listPosition);
        return mav;
    }

    public List<Position> getPositionList() {
        return positionService.listAllPositions();
    }

    public void savePosition(Position position) {
        positionService.savePosition(position);
    }

    public void deletePosition(Long code) {
        positionService.deletePosition(code);
    }

    public Position getPositionUI(Long code) throws EntityNotFoundException {
        return positionService.getPosition(code);
    }

    public Long count(){return positionService.count();}
}
