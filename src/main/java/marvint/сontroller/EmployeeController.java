package marvint.сontroller;

import lombok.SneakyThrows;
import marvint.domain.*;
import marvint.exceptions.EntityAlreadyExistException;
import marvint.exceptions.EntityNotFoundException;
import marvint.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String login) throws EntityNotFoundException {
        return employeeService.getEmployee(login);
    }

    public Employee createEmployee(Employee employee) throws EntityAlreadyExistException {
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
        ModelAndView mav = new ModelAndView("search"/*, "command", new EmployeeFilter()*/);
        return mav;
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void getImage(HttpServletResponse response, @PathVariable("id") String login) throws EntityNotFoundException, IOException {
        var employee = employeeService.getEmployee(login);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.getOutputStream().write(employee.getImage());
        response.setContentLength(employee.getImage().length);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("SpringWeb") EmployeeFilter employeeFilter, ModelMap model) {
        model.getAttribute(employeeFilter.getFirstName());
        model.getAttribute(employeeFilter.getLastName());
        model.getAttribute(employeeFilter.getSecondName());
        model.getAttribute(employeeFilter.getMail());
        model.getAttribute(employeeFilter.getPhone());

        List<Employee> listEmployee = employeeService.getEmployees(employeeFilter);
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("listEmployee", listEmployee);
        return mav;
    }


    public List<Employee> getEmployeeList() {
        return employeeService.listAllEmployees();
    }

    public void saveEmployee(Employee employee) throws EntityNotFoundException {
        employeeService.updateEmployee(employee);
    }

    @SneakyThrows
    public Employee getEmployeeByLogin(String login) {
        return employeeService.getEmployee(login);
    }

    public void deleteEmployeeById(String login) throws EntityNotFoundException {
        employeeService.deleteEmployee(login);
    }

    public List<Employee> listEmployeeByPosition(Position position) {
        return employeeService.listEmployeeByPosition(position);
    }

    public List<Employee> listEmployeeByFilter(EmployeeFilter employeeFilter) {
        return employeeService.getEmployees(employeeFilter);
    }

    public Long count(){return employeeService.count();}
}
