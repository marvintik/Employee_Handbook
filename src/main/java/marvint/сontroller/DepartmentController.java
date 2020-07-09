package marvint.сontroller;

import marvint.domain.DepartmentFilter;
import marvint.exceptions.EntityAlreadyExistException;
import marvint.exceptions.EntityNotFoundException;
import marvint.service.DepartmentService;
import marvint.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/(id}")
    public Department getDepartment(@PathVariable Long id) throws EntityNotFoundException {
        return departmentService.getDepartment(id);
    }

    @GetMapping
    public ModelAndView home() throws SQLException {
        List<Department> listDepartment = departmentService.listAll();
        ModelAndView mav = new ModelAndView("department");
        mav.addObject("listDepartment", listDepartment);
        return mav;
    }

    public List<Department> getDepartmentList() { return departmentService.listAllDepartments(); }

    public void createDepartment(Department department) {departmentService.createDepartment(department);}

    public void saveDepartment(Department department) {departmentService.saveDepartment(department);}

    public void deleteDepartmentById(Long id) {departmentService.deleteDepartment(id);}

    public Department getDepartmentById(Long id) throws EntityNotFoundException {return departmentService.getDepartment(id);}

    public Department getDepartmentByTitle(String title) {return departmentService.getByTitle(title);}

    public List<Department> listDepartmentByFilter(DepartmentFilter departmentFilter){return departmentService.listDepartmentByFilter(departmentFilter);}

    public Long count(){return departmentService.count();}

}
