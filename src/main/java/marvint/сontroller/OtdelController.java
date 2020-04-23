package marvint.сontroller;

import marvint.domain.Otdel;
import marvint.domain.Otdel;
import marvint.service.OtdelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
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


}
