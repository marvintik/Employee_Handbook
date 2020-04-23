package marvint.сontroller;

import marvint.Application;
import marvint.GUI.EmployeeForm;
import marvint.domain.Employee;
import marvint.domain.Filter;
import marvint.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/(id}")
    public Employee getEmployee(@PathVariable String login) {
        return employeeService.getEmployee(login);
    }

    @PostMapping
    public Employee createMovie(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public ModelAndView home() {
        List<Employee> listEmployee = employeeService.listAll();
        ModelAndView mav = new ModelAndView("employee");
        mav.addObject("listEmployee", listEmployee);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView filter() {
        ModelAndView mav = new ModelAndView("search"/*, "command", new Filter()*/);
        //System.out.println(filter());
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("SpringWeb") Filter filter, ModelMap model) {
        model.getAttribute(filter.getFirstName());
        model.getAttribute(filter.getLastName());
        model.getAttribute(filter.getSecondName());
        model.getAttribute(filter.getMail());
        model.getAttribute(filter.getPhone());

        List<Employee> listEmployee = employeeService.getEmployees(filter);
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("listEmployee", listEmployee);
        return mav;
    }


    public List<Employee> getEmployeeList() {
        return employeeService.listAllEmployees();
    }

    public void saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
    }

    public void deleteEmployeeById(String login){
        employeeService.deleteEmployee(login);
    }

}
