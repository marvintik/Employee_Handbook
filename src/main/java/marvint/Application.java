package marvint;

import marvint.GUI.MainForm;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
