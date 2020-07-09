package marvint.сontroller;

import marvint.domain.Department;
import marvint.domain.Otdel;
import marvint.domain.Otdel;
import marvint.domain.OtdelFilter;
import marvint.service.OtdelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class OtdelController {

    @Autowired
    OtdelService otdelService;

    @GetMapping("/{id}")
    public ModelAndView getOtdel(@PathVariable Long id) {
        Otdel otdel = otdelService.getOtdel(id);
        ModelAndView mav = new ModelAndView("otdelId");
        mav.addObject("otdel", otdel);
        return mav;
    }
    @PostMapping
    public Otdel createMovie(@RequestBody Otdel otdel) {
        return otdelService.createOtdel(otdel);
    }

    @GetMapping
    public ModelAndView home() throws SQLException {
        List<Otdel> listOtdel = otdelService.listAll();
        ModelAndView mav = new ModelAndView("otdel");
        mav.addObject("listOtdel", listOtdel);
        return mav;
    }

    public List<Otdel> getOtdelList() {
        return otdelService.listAllOtdels();
    }

    public void saveOtdel(Otdel otdel) {
        otdelService.saveOtdel(otdel);
    }

    public void deleteOtdel(Long id) {otdelService.deleteOtdel(id);}

    public Otdel getOtdelById(Long id) {return otdelService.getOtdel(id);}
    public Otdel getOtdelByTitle(String title) {return otdelService.getByTitle(title);}

    public Otdel getOtdelByTitleAndDepantment(String title, Department department){
        return otdelService.getOtdelByTitleAndDepantment(title, department);
    }

    public List<Otdel> getOtdelsByFilter(OtdelFilter otdelFilter){return otdelService.getOtdelsByFilter(otdelFilter);}

    public Long count(){return otdelService.count();}
}
