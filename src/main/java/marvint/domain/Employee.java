package marvint.domain;

import lombok.Data;
import lombok.ToString;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Phone> phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Mail> mail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "otdel_id")
    private Otdel otdel;
}
