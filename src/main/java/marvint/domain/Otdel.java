package marvint.domain;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Otdel {
    @Id
    @Column(unique = true)
    private Long id;

    private String title;
    private String address;

    @OneToMany(mappedBy = "otdel", cascade = CascadeType.PERSIST)
    private List<Employee> employees;
}
