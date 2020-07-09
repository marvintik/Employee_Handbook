package marvint;

import lombok.SneakyThrows;
import marvint.GUI.MainForm;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class Application {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false).run(args);
        initUI(context);
    }

    private static void initUI(ConfigurableApplicationContext context) throws UnsupportedLookAndFeelException {
        MainForm form = context.getBean(MainForm.class);
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        form.init();
    }

    @Bean
    public MainForm fun() {
        return new MainForm();
    }

}
