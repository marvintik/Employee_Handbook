package marvint.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    private String login;

    @ManyToOne
    private Position position;

    private String firstName;
    private String lastName;
    private String secondName;
    private LocalDate date;

    private String photo;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_login")
    private List<Phone> phone;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_login")
    private List<Mail> mail;

    @ManyToOne
    private Otdel otdel;
}
