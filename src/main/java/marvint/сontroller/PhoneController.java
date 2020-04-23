package marvint.сontroller;

import marvint.domain.Phone;
import marvint.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/v1/phones")
public class PhoneController {

    @Autowired
    PhoneService phoneService;


    @GetMapping("/(id}")
    public Phone getPhone(@PathVariable Long id) {
        return phoneService.getPhone(id);
    }

    @PostMapping
    public Phone createMovie(@RequestBody Phone phone) {
        return phoneService.createPhone(phone);
    }

    @GetMapping
    public ModelAndView home() {
        List<Phone> listPhone = phoneService.listAll();
        ModelAndView mav = new ModelAndView("phone");
        mav.addObject("listPhone", listPhone);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView filter() {
        ModelAndView mav = new ModelAndView("search"/*, "command", new Filter()*/);
        //System.out.println(filter());
        return mav;
    }
    


    public List<Phone> getPhoneList() {
        return phoneService.listAllPhones();
    }

    public void savePhone(Phone phone) {
        phoneService.savePhone(phone);
    }

}
