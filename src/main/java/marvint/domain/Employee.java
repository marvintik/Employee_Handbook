package marvint.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    private String login;

    @ManyToOne
    private Position position;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String secondName;
    @NotNull
    private LocalDate date;

    private String photo;
    private byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Phone> phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Mail> mail;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "otdel_id")
    private Otdel otdel;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;
}
