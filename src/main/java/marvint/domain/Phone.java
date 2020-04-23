package marvint.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class Phone {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_login")
    private Employee employee;

    private String phone;
}
