package marvint.сontroller;

import marvint.service.DepartmentService;
import marvint.service.EmployeeService;
import marvint.service.OtdelService;
import marvint.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionService positionService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    OtdelService otdelService;


    @GetMapping({"/", "/hello"})
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("department", departmentService.count());
        mav.addObject("otdel", otdelService.count());
        mav.addObject("position", positionService.count());
        mav.addObject("employee", employeeService.count());
        return mav;
    }
}
