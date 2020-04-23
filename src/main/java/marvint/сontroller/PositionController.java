package marvint.сontroller;

import marvint.domain.Position;
import marvint.domain.Filter;
import marvint.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/v1/positions")
public class PositionController {

    @Autowired
    PositionService positionService;


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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView filter() {
        ModelAndView mav = new ModelAndView("search"/*, "command", new Filter()*/);
        //System.out.println(filter());
        return mav;
    }

 /**   @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("SpringWeb") Filter filter, ModelMap model) {
        model.getAttribute(filter.getFirstName());
        model.getAttribute(filter.getLastName());
        model.getAttribute(filter.getSecondName());
        model.getAttribute(filter.getMail());
        model.getAttribute(filter.getPhone());

        List<Position> listPosition = positionService.getPositions(filter);
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("listPosition", listPosition);
        return mav;
    }*/


    public List<Position> getPositionList() {
        return positionService.listAllPositions();
    }

    public void savePosition(Position position) {
        positionService.savePosition(position);
    }

}
