package marvint.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Department {
    @Id
    @Column(unique = true)
    private Long id;

    private String title;
    private String address;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private List<Otdel> otdel;
}
