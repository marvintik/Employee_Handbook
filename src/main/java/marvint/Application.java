package marvint;

import marvint.GUI.EmployeeForm;
import marvint.GUI.MainForm;
import marvint.domain.Employee;
import marvint.domain.Mail;
import marvint.domain.Phone;
import marvint.сontroller.DepartmentController;
import marvint.сontroller.EmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
    //    var c = SpringApplication.run(Application.class, args);
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false).run(args);
        MainForm form = context.getBean(MainForm.class);
    //    EmployeeForm employeeForm = context.getBean(EmployeeForm.class);
        form.init();
  //      employeeForm.initFrame();
    }

    @Bean
    public MainForm fun() {
        return new MainForm();
    }

}

//    try {
//         ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false).run(args);

//         EmployeeController controller = context.getBean(EmployeeController.class);
//        MainForm form = new MainForm(controller);
//      DepartmentController departmentController = context.getBean(DepartmentController.class);
//      form.init();
//    } catch (Throwable e) {
//         System.out.println(e);
//     }
// }
