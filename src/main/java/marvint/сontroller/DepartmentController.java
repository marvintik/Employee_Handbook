package marvint.сontroller;

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
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/(id}")
    public Department getDepartment(@PathVariable Long id) {
        return departmentService.getDepartment(id);
    }



    @PostMapping
    public Department createMovie(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping
    public ModelAndView home() throws SQLException {
        List<Department> listDepartment = departmentService.listAll();
        ModelAndView mav = new ModelAndView("department");
        mav.addObject("listDepartment", listDepartment);
        return mav;
    }

    public List<Department> getDepartmentList() { return departmentService.listAllDepartments(); }

    public void saveDepartment(Department department) {departmentService.saveDepartment(department);}

    public void deleteDepartmentById(Long id) {departmentService.deleteDepartment(id);}
}
