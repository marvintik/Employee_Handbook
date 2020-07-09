package marvint.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Otdel {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String address;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "otdel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
